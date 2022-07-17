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
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5)
//                    .setMaxResults(8)
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member = " + member.getName());
//            }
//            Member findMember = em.find(Member.class, 1L);
//
//            findMember.setName("ByeA");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
