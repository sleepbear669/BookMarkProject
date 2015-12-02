package gom.cave.sleep.bookmark.repository;

import gom.cave.sleep.bookmark.model.BookmarkCard;
import gom.cave.sleep.bookmark.model.Member;
import org.junit.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

/**
 * Created by sleepbear on 2015. 12. 2..
 */
public class BookmarkCardRepositoryTest {
    @Test
    public void testAddAndGetTest() throws Exception {
        // Given
        final MemberRepository memberRepository = new MemberRepository();
        final BookmarkCardRepository bookmarkCardRepository = new BookmarkCardRepository();
        final String testerEmail = "test@test.com";
        final Member member = memberRepository.get(testerEmail);
        final BookmarkCard bookmarkCard = new BookmarkCard();

        bookmarkCard.setMemberId(member.getId());
        bookmarkCard.setPhrase("아아, 인생 가시밭길");
        // When
        bookmarkCardRepository.add(bookmarkCard);
        List<BookmarkCard> bookmarkCardList = bookmarkCardRepository.getByMemberId(member.getId());
        // Then
        assertThat(bookmarkCardList.size(), greaterThan(1));

        final List<BookmarkCard> collect = bookmarkCardList.stream()
                .limit(1)
                .filter(bc -> bc.getPhrase().equals(bookmarkCard.getPhrase()))
                .collect(toList());
        final BookmarkCard fetchedBookmarkCard = collect.get(0);
        assertThat(fetchedBookmarkCard.getMemberId(), is(bookmarkCard.getMemberId()));
        assertThat(fetchedBookmarkCard.getPhrase(), is(bookmarkCard.getPhrase()));

    }
}