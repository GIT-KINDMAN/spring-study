package hello.hellospring.repository;
import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); // 옵셔널은 널을 처리하는 방법. 옵셔널로 감싸서 반환. 이유는? 나중에. 자바8 기능
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
