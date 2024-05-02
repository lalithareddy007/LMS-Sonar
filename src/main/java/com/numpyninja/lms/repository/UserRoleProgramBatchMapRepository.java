package com.numpyninja.lms.repository;

import com.numpyninja.lms.entity.Batch;
import com.numpyninja.lms.entity.UserRoleProgramBatchMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleProgramBatchMapRepository extends JpaRepository<UserRoleProgramBatchMap, Long> {

    Optional<UserRoleProgramBatchMap> findByUser_UserIdAndRoleRoleIdAndProgram_ProgramIdAndBatch_BatchId
            (String userId, String roleId, Long programId, Integer batchId);

    Optional<UserRoleProgramBatchMap> findByUser_UserIdAndRoleRoleIdAndUserRoleProgramBatchStatusEqualsIgnoreCase
            (String userId, String roleId, String status);


    
	
	
	List<UserRoleProgramBatchMap> findByBatch_BatchId(Integer batchid);

	

    List<UserRoleProgramBatchMap> findByUser_UserId(String userId);
    List<UserRoleProgramBatchMap> findByProgram_ProgramId(Long programId);


    List<UserRoleProgramBatchMap> findByRole_RoleId(String roleId);

    List<UserRoleProgramBatchMap> findBatch_BatchIdByUser_UserId(String userId);
}
