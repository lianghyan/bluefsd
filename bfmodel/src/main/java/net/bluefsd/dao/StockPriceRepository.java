package net.bluefsd.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.bluefsd.entity.StockPrice;

@Repository(value = "stockPriceRepository")
public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {
	// select * from stock_price where date(cur_time) between '2019-05-13' and
	// '2019-05-31';
	@Transactional(readOnly = true)
	@Query(value = "SELECT s FROM StockPrice s where stockCd=:stockCd and curTime in (select lastDay from BFWeekDay bfw where date(lastDay) between :fromDate and :toDate) order by s.curTime")
	public List<StockPrice> findWeekByStockCd(@Param("stockCd") String stockCd, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);

	@Transactional(readOnly = true)
	@Query(value = "SELECT s FROM StockPrice s where stockCd=:stockCd and curTime in (select lastDay from BFMonthDay bfm where date(lastDay) between :fromDate and :toDate)  order by s.curTime")
	public List<StockPrice> findMonthByStockCd(@Param("stockCd") String stockCd, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);

	@Transactional(readOnly = true)
	@Query(value = "SELECT s FROM StockPrice s where stockCd=:stockCd and curTime in (select lastDay from BFMonth6Day bfm where date(lastDay) between :fromDate and :toDate)  order by s.curTime")
	public List<StockPrice> findMonth6ByStockCd(@Param("stockCd") String stockCd, Date from, Date to);

	@Transactional(readOnly = true)
	@Query(value = "SELECT s FROM StockPrice s where stockCd=:stockCd and curTime in (select lastDay from BFYearDay bfm where date(lastDay) between :fromDate and :toDate)  order by s.curTime")
	public List<StockPrice> findYearByStockCd(@Param("stockCd") String stockCd, Date from, Date to);

}
