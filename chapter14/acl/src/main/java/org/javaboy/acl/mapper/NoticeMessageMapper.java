package org.javaboy.acl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.acl.model.NoticeMessage;

import java.util.List;


@Mapper
public interface NoticeMessageMapper {
    List<NoticeMessage> findAll();

    NoticeMessage findById(Integer id);

    void save(NoticeMessage noticeMessage);

    void update(NoticeMessage noticeMessage);
}
