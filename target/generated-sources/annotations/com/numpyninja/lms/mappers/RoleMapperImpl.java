package com.numpyninja.lms.mappers;

import com.numpyninja.lms.dto.RoleDto;
import com.numpyninja.lms.entity.Role;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-02T10:21:19-0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDto toRoleDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setRoleId( role.getRoleId() );
        roleDto.setRoleName( role.getRoleName() );
        roleDto.setRoleDesc( role.getRoleDesc() );
        roleDto.setCreationTime( role.getCreationTime() );
        roleDto.setLastModTime( role.getLastModTime() );

        return roleDto;
    }

    @Override
    public Role torole(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setRoleId( roleDto.getRoleId() );
        role.setRoleName( roleDto.getRoleName() );
        role.setRoleDesc( roleDto.getRoleDesc() );
        role.setCreationTime( roleDto.getCreationTime() );
        role.setLastModTime( roleDto.getLastModTime() );

        return role;
    }

    @Override
    public List<Role> toRoleList(List<RoleDto> roleDtos) {
        if ( roleDtos == null ) {
            return null;
        }

        List<Role> list = new ArrayList<Role>( roleDtos.size() );
        for ( RoleDto roleDto : roleDtos ) {
            list.add( torole( roleDto ) );
        }

        return list;
    }

    @Override
    public List<RoleDto> toRoleDtoList(List<Role> roles) {
        if ( roles == null ) {
            return null;
        }

        List<RoleDto> list = new ArrayList<RoleDto>( roles.size() );
        for ( Role role : roles ) {
            list.add( toRoleDto( role ) );
        }

        return list;
    }
}
