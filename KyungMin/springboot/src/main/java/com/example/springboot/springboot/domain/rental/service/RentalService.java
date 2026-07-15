package com.example.springboot.springboot.domain.rental.service;

import com.example.springboot.springboot.domain.book.entity.Book;
import com.example.springboot.springboot.domain.book.exception.BookErrorCode;
import com.example.springboot.springboot.domain.book.exception.BookException;
import com.example.springboot.springboot.domain.book.repository.BookRepository;
import com.example.springboot.springboot.domain.member.entity.Member;
import com.example.springboot.springboot.domain.member.exception.MemberErrorCode;
import com.example.springboot.springboot.domain.member.exception.MemberException;
import com.example.springboot.springboot.domain.member.repository.MemberRepository;
import com.example.springboot.springboot.domain.rental.dto.RentalReqDTO;
import com.example.springboot.springboot.domain.rental.dto.RentalResDTO;
import com.example.springboot.springboot.domain.rental.entity.Rental;
import com.example.springboot.springboot.domain.rental.enums.RentalStatus;
import com.example.springboot.springboot.domain.rental.exception.RentalErrorCode;
import com.example.springboot.springboot.domain.rental.exception.RentalException;
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
                .orElseThrow(() ->
                        new MemberException(
                                MemberErrorCode.MEMBER_NOT_FOUND
                        )
                );

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() ->
                        new BookException(
                                BookErrorCode.BOOK_NOT_FOUND
                        )
                );

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
                .orElseThrow(() ->
                        new RentalException(
                                RentalErrorCode.RENTAL_NOT_FOUND
                        )
                );

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

    @Transactional
    public RentalResDTO.OngoingRentalListDto getOngoingRentals(RentalReqDTO.OngoingRentalSearchDto request) {
        Page<Rental> rentalPage = rentalRepository.findRentalsByMemberIdAndStatus(
                request.getMemberId(),
                RentalStatus.RENTED,
                PageRequest.of(request.getPage(), request.getSize())
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

        return new RentalResDTO.OngoingRentalListDto(
                rentals,
                request.getPage(),
                request.getSize(),
                rentalPage.getTotalPages(),
                rentalPage.getTotalElements(),
                rentalPage.isFirst(),
                rentalPage.isLast()
        );
    }
}
