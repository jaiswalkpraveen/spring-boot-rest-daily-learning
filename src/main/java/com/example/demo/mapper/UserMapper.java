package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.entity.UserDetails;

//disable auto suggestions
@Mapper(componentModel = "spring")
public interface UserMapper {

    // toEntity (UserRequest → User)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userDetails", expression = "java(toUserDetails(request))")
    User toEntity(UserRequest request);

    // toUserDetails (UserRequest → UserDetails)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    UserDetails toUserDetails(UserRequest request);

    // User Entity → UserResponse DTO
    @Mapping(target = "address", source = "userDetails.address")
    @Mapping(target = "phone", source = "userDetails.phone")
    UserResponse toUserResponse(User user);
}
