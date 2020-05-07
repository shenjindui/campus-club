package cn.fjut.gmxx.campusclub.baseddct.repository;

import cn.fjut.gmxx.campusclub.baseddct.entity.BaseDdctEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * 数据字典数据缓存 组件类
 * @author shenjindui
 *
 */
@Repository
public class DictCacheRepository {

	/**
	 * 锁
	 */
	Lock lock = new ReentrantLock();

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	BaseDdctRepository baseDdctRepository;

	private HashOperations<String, String, BaseDdctEntity> dictOpsForHash;

	//private HashOperations<String, String, List<BaseDdctEntity>> dictParentOpsForHash;
	
	@PostConstruct
	private void init() {
		dictOpsForHash = redisTemplate.opsForHash();
		//dictParentOpsForHash = redisTemplate.opsForHash();
	}

	/**
	 * 字典数据
	 */
	private final static String DICT_DETAIL_KEY = "dict:detail";
	private final static String DICT_LIST_KEY = "dict:list";

	/**
	 * 获取字典
	 * @param code 字典编码
	 * @return
	 */
	public BaseDdctEntity findByCode(String code) {
		return dictOpsForHash.get(DICT_DETAIL_KEY, code);
	}

	/**
	 * 获取子字典列表
	 * @param parentCode 父字典编码
	 * @return
	 */
	/*public List<BaseDdctEntity> findByParentCode(String parentCode) {
		return dictParentOpsForHash.get(DICT_LIST_KEY, parentCode);
	}*/
	
	/**
	 * 刷新缓存的所有数据字典数据
	 */
	public void refreshDictAll() {
		// 模拟数据准备
		List<BaseDdctEntity> dictList = getDictList();

		// list 转 map 方便调用 redisTemplate 接口批量插入
		Map<String, BaseDdctEntity> dictMap = dictList.stream().collect(Collectors.toMap(BaseDdctEntity::getDctTpCd, dict -> dict));
		
		// 按父字典编码进行分组（生产环境不建议使用递归查询组装数据，可以批量查询后使用 Java8 集合的分组功能）
		//Map<String, List<BaseDdctEntity>> dictParentMap = dictList.stream().collect(Collectors.groupingBy(BaseDdctEntity::getParentCode));

		// 加锁，分布式下需使用分布式锁保证数据一致性
		lock.lock();
		
		try {
			// 先删除旧的缓存，使用 pipeline 提搞效率
			redisTemplate.executePipelined(new RedisCallback<Boolean>() {

				@SuppressWarnings("unchecked")
				@Override
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
					RedisSerializer<String> keySerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
					
					connection.del(keySerializer.serialize(DICT_DETAIL_KEY));
					connection.del(keySerializer.serialize(DICT_LIST_KEY));
					
					// 此处返回什么没有意义
					return null;
				}
				
			});
			
			// 先删除旧的缓存
//			redisTemplate.delete(DICT_DETAIL_KEY);
//			redisTemplate.delete(DICT_LIST_KEY);
			
			// 缓存 detail，底层调用的是 redis 的 hmset 命令
			dictOpsForHash.putAll(DICT_DETAIL_KEY, dictMap);
			
			// 缓存 list，底层调用的是 redis 的 hmset 命令
			//dictParentOpsForHash.putAll(DICT_LIST_KEY, dictParentMap);
		} finally {
			// 解锁
			lock.unlock();
		}
	}

	/**
	 * 模拟数据准备，实际应从数据库查询获取数据
	 * @return
	 */
	private List<BaseDdctEntity> getDictList() {
		return  baseDdctRepository.findAll();
	}

}
