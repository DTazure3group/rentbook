package book.rental.system;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MypageRepository extends CrudRepository<Mypage, Long> {

    Optional<Mypage> findByRentid(Long rentalId);
    Optional<Mypage> findByCustomerid(Long customerId);

}