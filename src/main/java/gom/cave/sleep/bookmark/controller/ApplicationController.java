package gom.cave.sleep.bookmark.controller;

import gom.cave.sleep.bookmark.model.Book;
import gom.cave.sleep.bookmark.model.BookmarkCard;
import gom.cave.sleep.bookmark.model.Comment;
import gom.cave.sleep.bookmark.model.Member;
import gom.cave.sleep.bookmark.model.dto.BookmarkCardDto;
import gom.cave.sleep.bookmark.model.dto.WriteCard;
import gom.cave.sleep.bookmark.model.dto.loginMember;
import gom.cave.sleep.bookmark.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by sleepbear on 2015. 12. 2..
 */
@RestController
@RequestMapping("api")
@CrossOrigin
public class ApplicationController {

    @Inject
    private MemberRepository memberRepository;

    @Inject
    private ModelMapper modelMapper;

    @Inject
    private BookmarkCardRepository bookmarkCardRepository;

    @Inject
    private BookRepository bookRepository;

    @Inject
    BookmarkerRepository bookmarkerRepository;

    @Inject
    CommentRepository commentRepository;

    @RequestMapping(value = "join", method = POST)
    public Boolean join(@RequestBody Member member) throws SQLException, ClassNotFoundException {
        final Boolean exist = memberRepository.existMember(member.getEmail());
        Boolean status = false;
        if (!exist) {
            memberRepository.add(member);
            status = true;
        }
        return status;
    }

    @RequestMapping(value = "login", method = POST)
    public loginMember login(@RequestBody Member member) throws SQLException, ClassNotFoundException {
        final Boolean existMember = memberRepository.existMember(member.getEmail());
        final loginMember loginMember = new loginMember();
        loginMember.setStatus(false);
        if (existMember) {
            final Member member1 = memberRepository.getByEmail(member.getEmail());
            if (member1.getPassword().equals(member.getPassword())) {
                modelMapper.map(member1, loginMember);
                loginMember.setStatus(true);
            }
        }
        return loginMember;
    }

    @RequestMapping(value = "{memberId}/insert/{cardId}", method = GET)
    public void insertBookmark(@PathVariable("memberId") long memberId, @PathVariable("cardId") long cardId) throws SQLException, ClassNotFoundException {
        if (!bookmarkerRepository.exists(memberId, cardId)) {
            bookmarkerRepository.add(memberId, cardId);
        }
    }

    @RequestMapping(value = "write/card", method = POST)
    public void writeCard(@RequestBody WriteCard writeCard) throws SQLException, ClassNotFoundException {
        final Long bookId = bookRepository.getIdByName(writeCard.getTitle());
        final BookmarkCard bookmarkCard = modelMapper.map(writeCard, BookmarkCard.class);
        bookmarkCard.setBookId(bookId);
        bookmarkCardRepository.add(bookmarkCard);
    }

    @RequestMapping(value = "write/comment", method = POST)
    public Boolean writeComment(@RequestBody Comment comment) throws SQLException, ClassNotFoundException {
        commentRepository.add(comment);
        return Boolean.valueOf("write comment");
    }

    @RequestMapping(value = "bookmark/card", method = GET)
    public List<BookmarkCardDto> fetchAllCard() throws SQLException, ClassNotFoundException {
        final List<BookmarkCardDto> allCard = bookmarkCardRepository.findAll();
        allCard.stream()
                .forEach(bookmarkCardDto -> {
                    try {
                        final List<Comment> commentList = commentRepository.getById(bookmarkCardDto.getId());
                        bookmarkCardDto.setCommentList(commentList);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });
        return allCard;
    }

    @RequestMapping(value = "bookmark/card/{memberId}", method = GET)
    public List<BookmarkCardDto> fetchCardById(@PathVariable("memberId") long memberId) throws SQLException, ClassNotFoundException {
        final List<BookmarkCardDto> membersCard= bookmarkCardRepository.getByMemberId(memberId);
        return membersCard;
    }

    @RequestMapping(value = "bookmarker/{memberId}", method = GET)
    public List<BookmarkCardDto> fetchBookMarkers(@PathVariable("memberId") long memberId) throws SQLException, ClassNotFoundException {
        final List<BookmarkCardDto> bookmarkCardDtoList = bookmarkerRepository.getByMemberId(memberId);
        return bookmarkCardDtoList;
    }

    @RequestMapping(value = "bookInfo", method = GET)
    public List<Book> fetchComments() throws SQLException, ClassNotFoundException {
        final List<Book> bookList= bookRepository.findAll();
        return bookList;
    }

}
