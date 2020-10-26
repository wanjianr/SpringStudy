package com.douye.mapper;

import com.douye.pojo.Payment;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class UserMapperImpl implements UserMapper {

    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<Payment> findData() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.findData();
    }
}
