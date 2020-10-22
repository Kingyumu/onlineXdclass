package net.xdclass.online_xdclass.mapper;

import net.xdclass.online_xdclass.model.entity.PlayRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayRecordMapper {

    int saveRecord(PlayRecord playRecord);

}
