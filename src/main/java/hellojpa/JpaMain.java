package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //code
        try {

            //영속
            Member member = new Member(200L, "member200");
            em.persist(member);

            em.flush();
            System.out.println("==============");

            /*Member member = em.find(Member.class, 150L);
            member.setName("Z");

            //em.persist(member); // 이렇게 해줘야하는거 아냐? 라고 생각하는데 내가 찾아온 다음에 변경하면 그대로 반영
            // => 변경 감지, 동작 방식 : 영속석 엔티티
            System.out.println("==============");

            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            em.persist(member1);
            em.persist(member2);

            System.out.println("==============");

            //비영속
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJpa");

            //영속
            System.out.println("===BEFORE===");
            em.persist(member);
            em.detach(member);
            System.out.println("===AFTER===");
            //커밋하기 직전에 찍힘 => 이상한 메커니즘

            Member findMember = em.find(Member.class, 100L);

            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.Name = " + findMember.getName());

            Member findMember1 = em.find(Member.class, 100L);
            Member findMember2 = em.find(Member.class, 100L);

            System.out.println("result = " + (findMember1 == findMember2));

            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member = " + member.getName());
            }
            Member findMember = em.find(Member.class, 1L);

            findMember.setName("ByeA");*/

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
