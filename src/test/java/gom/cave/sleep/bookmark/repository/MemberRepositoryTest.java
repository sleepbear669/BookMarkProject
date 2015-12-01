package gom.cave.sleep.bookmark.repository;

import gom.cave.sleep.bookmark.model.Member;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by sleepbear on 2015. 12. 1..
 */
public class MemberRepositoryTest {

    @Test
    public void testAddAndGetMemberRepositoryTest() throws Exception {
        final String email = "sleepbear669@gmail.com1";
        final String nickname = "sleepbear";
        final String password = "gom0119!1";
        final Member member = new Member();
        // Given
        final MemberRepository memberRepository = new MemberRepository();
        memberRepository.delete(email);
        member.setEmail(email);
        member.setPassword(password);
        member.setNickname(nickname);
        // When
        memberRepository.add(member);
        final Member member1 = memberRepository.get(member.getEmail());
        // Then
        assertThat(member1.getEmail(), is(member.getEmail()));
        assertThat(member1.getNickname(), is(member.getNickname()));
        assertThat(member1.getPassword(), is(member.getPassword()));
    }
}