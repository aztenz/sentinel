package com.j2o.sentinel.service;

import com.j2o.sentinel.dto.request.user.PostUserRQ;
import com.j2o.sentinel.dto.request.user.PutUserRQ;
import com.j2o.sentinel.dto.response.user.UserDetails;
import com.j2o.sentinel.dto.response.user.UserListItem;
import com.j2o.sentinel.dto.response.user.PostUserRSP;
import com.j2o.sentinel.dto.response.user.PutUserRSP;
import com.j2o.sentinel.model.User;
import com.j2o.sentinel.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService
        extends GenericService<User,
        Integer,
        UserRepository,
        PostUserRQ,
        PutUserRQ,
        UserListItem,
        UserDetails,
        PostUserRSP,
        PutUserRSP> {
    public UserService(UserRepository UserRepository) {
        super(UserRepository,
                User.class,
                PostUserRSP.class,
                PutUserRSP.class,
                UserListItem.class,
                UserDetails.class);
    }
}
