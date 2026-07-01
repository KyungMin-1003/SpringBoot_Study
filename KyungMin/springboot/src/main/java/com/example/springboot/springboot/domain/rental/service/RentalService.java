package com.example.springboot.springboot.domain.rental.service;

import com.example.springboot.springboot.domain.book.entity.Book;
import com.example.springboot.springboot.domain.book.repository.BookRepository;
import com.example.springboot.springboot.domain.member.entity.Member;
import com.example.springboot.springboot.domain.member.repository.MemberRepository;
import com.example.springboot.springboot.domain.rental.dto.RentalReqDTO;
import com.example.springboot.springboot.domain.rental.dto.RentalResDTO;
import com.example.springboot.springboot.domain.rental.entity.Rental;
import com.example.springboot.springboot.domain.rental.repository.RentalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    @Transactional
    public RentalResDTO.CreateRentalResultDto createRental(Long bookId, RentalReqDTO.CreateRentalDto request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("책을 찾을 수 없습니다."));

        LocalDateTime now = LocalDateTime.now();

        Rental rental = Rental.builder()
                .member(member)
                .book(book)
                .rentedAt(now)
                .dueDate(now.plusDays(14))
                .build();

        Rental savedRental = rentalRepository.save(rental);

        return new RentalResDTO.CreateRentalResultDto(
                savedRental.getId(),
                book.getId(),
                member.getId(),
                savedRental.getRentedAt(),
                savedRental.getDueDate()
        );
    }

    @Transactional
    public RentalResDTO.ReturnRentalResultDto returnRental(Long rentalId, RentalReqDTO.ReturnRentalDto request) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("대여 정보를 찾을 수 없습니다."));

        rental.returnBook(request.getMemo());

        return new RentalResDTO.ReturnRentalResultDto(
                rental.getId(),
                rental.getBook().getId(),
                rental.getReturnedAt(),
                "도서 반납 완료"
        );
    }

    @Transactional
    public RentalResDTO.RentalListDto getMemberRentals(Long memberId, Integer page, Integer size) {
        Page<Rental> rentalPage = rentalRepository.findRentalsByMemberId(
                memberId,
                PageRequest.of(page, size)
        );

        List<RentalResDTO.RentalInfoDto> rentals = rentalPage.getContent().stream()
                .map(rental -> new RentalResDTO.RentalInfoDto(
                        rental.getId(),
                        rental.getBook().getId(),
                        rental.getBook().getTitle(),
                        rental.getRentedAt(),
                        rental.getDueDate(),
                        rental.getReturnedAt(),
                        rental.getStatus().name()
                ))
                .toList();

        return new RentalResDTO.RentalListDto(
                rentals,
                "page=" + page + ", size=" + size +
                        ", totalPages=" + rentalPage.getTotalPages() +
                        ", totalElements=" + rentalPage.getTotalElements()
        );
    }
}
