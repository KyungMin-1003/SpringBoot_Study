package com.example.springboot.springboot.domain.rental.repository;

import com.example.springboot.springboot.domain.rental.entity.Rental;
import com.example.springboot.springboot.domain.rental.enums.RentalStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    @Query(
            value = "SELECT r FROM Rental r WHERE r.member.id = :memberId ORDER BY r.id DESC",
            countQuery = "SELECT count(r) FROM Rental r WHERE r.member.id = :memberId"
    )
    Page<Rental> findRentalsByMemberId(@Param("memberId") Long memberId, Pageable pageable);

    @Query(
            value = "SELECT r FROM Rental r WHERE r.member.id = :memberId AND r.status = :status ORDER BY r.id DESC",
            countQuery = "SELECT count(r) FROM Rental r WHERE r.member.id = :memberId AND r.status = :status"
    )
    Page<Rental> findRentalsByMemberIdAndStatus(
            @Param("memberId") Long memberId,
            @Param("status") RentalStatus status,
            Pageable pageable
    );
}
