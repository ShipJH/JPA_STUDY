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

        try {
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member);
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMEmber.id = " + findMember.getId());
//            System.out.println("findMEmber.name = " + findMember.getName());
//
//            findMember.setName("HelloJPA");
//            em.persist 하지않아도, commit시점에 변경되었다면 Update를 실행하게 된다.

            //JPQL 맛보기
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1) // page 1 ~
                    .setMaxResults(10) // page ~ 10
                    .getResultList();

            for (Member member : result) {
                System.out.println("name = " + member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

