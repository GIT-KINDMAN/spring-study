package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;
    // JPA는 전부 EntityManager라는 것으로 돌아감 // 스프링 부트가 알아서 엔티티매니저를 생성해줌. 현재 데이터베이스랑 연결해서 만들어줌. 그래서 만든걸 injection만 하면 됨.
    // 얘는 데이터소스를 내부적으로 들고 있어서 DB랑 연결하고 하는걸 내부적으로 다 처리를 한다

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // persist는 영구저장하다, 영구적이다 라는 뜻
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList(); // Ctrl + Alt + N => return문 inline 처리해줌
        // JPA는 * 등이 아니라 객체 자체를 지정해서 select해준다
    }
}
