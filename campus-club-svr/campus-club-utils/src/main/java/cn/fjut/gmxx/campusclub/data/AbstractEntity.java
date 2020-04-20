package cn.fjut.gmxx.campusclub.data;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

public abstract class AbstractEntity<ID> implements Serializable {
    private static final long serialVersionUID = 4115015592686456241L;

    public AbstractEntity() {
    }

    public abstract ID getId();

    public abstract void setId(ID var1);

    public boolean isNew() {
        return null == this.getId();
    }

    public boolean equals(Object obj) {
        if(null == obj) {
            return false;
        } else if(this == obj) {
            return true;
        } else if(!this.getClass().equals(obj.getClass())) {
            return false;
        } else {
            AbstractEntity<?> that = (AbstractEntity)obj;
            return null == this.getId()?false:this.getId().equals(that.getId());
        }
    }

    public int hashCode() {
        int hashCode = 17;
         hashCode = hashCode + (null == this.getId()?0:this.getId().hashCode() * 31);
        return hashCode;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
