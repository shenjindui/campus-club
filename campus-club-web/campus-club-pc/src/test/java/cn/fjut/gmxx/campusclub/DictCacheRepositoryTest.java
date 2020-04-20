/*
package cn.fjut.gmxx.campusclub;

import cn.fjut.gmxx.campusclub.baseclubscore.entity.BaseClubScoreEntity;
import cn.fjut.gmxx.campusclub.baseclubscore.mapper.IBaseClubScoreMapper;
import cn.fjut.gmxx.campusclub.baseddct.entity.BaseDdctEntity;
import cn.fjut.gmxx.campusclub.baseddct.repository.DictCacheRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


*/
/**
 * 
 * @author lingyuwang
 *
 *//*

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CampusClubPcApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT  websocket导致spring boot 项目单元测试启动失败的问题解决
public class DictCacheRepositoryTest {

	@Autowired
	DictCacheRepository dictCacheRepository;

	@Autowired
	IBaseClubScoreMapper baseClubScoreMapper;

	@Before
	public void refreshDictAll() {
		dictCacheRepository.refreshDictAll();
	}
	
	@Test
	public void findByCode() {
		System.out.println();
		System.out.println("============================ findByCode start ============================");
		
		String code = "collegeCd1";
		BaseDdctEntity dict = dictCacheRepository.findByCode(code);
		System.out.println(dict);
		
		code = "collegeCd1";
		dict = dictCacheRepository.findByCode(code);
		System.out.println(dict);
		
		System.out.println("============================ findByCode end ============================");
		System.out.println();
	}
	@Test
	public void testBaseMapper() {
	    //while (true){
            BaseClubScoreEntity baseClubScoreEntity = new BaseClubScoreEntity();
            baseClubScoreEntity.setUuid("4028819071109d350171109ddfc50005");
            //baseClubScoreEntity=baseClubScoreMapper.selectById("4028819071109d350171109ddfc50005");
            baseClubScoreEntity=baseClubScoreMapper.get(baseClubScoreEntity);
            System.out.print(baseClubScoreEntity.toString());
       // }

	}

	

}
*/
