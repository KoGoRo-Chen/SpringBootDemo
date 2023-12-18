package mvc.dao.spform;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mvc.bean.spform.EducationData;
import mvc.bean.spform.InterestData;
import mvc.bean.spform.SexData;

@Repository
public class DataDaoImplMySQL implements DataDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//SQL 查詢語句：sqlAll 用於查詢所有的基礎資料，sqlSingle 用於根據指定的 id 查詢單個基礎資料。這些 SQL 查詢語句使用了 ? 作為參數的佔位符。
	private final String sqlAll = "SELECT itemId as id, itemName as name FROM web.basedata where groupName = ?";
	private final String sqlSingle = "SELECT itemId as id, itemName as name FROM web.basedata where groupName = ? and itemId = ?";
	
	@Override
	public List<EducationData> findAllEducationDatas() {
		return jdbcTemplate.query(sqlAll, new BeanPropertyRowMapper<>(EducationData.class), "Education");
	}
	//BeanPropertyRowMapper 的使用：BeanPropertyRowMapper 用於將查詢結果的每一行映射為一個 Java 對象。這裡的泛型參數指定了映射的目標類別。
	
	@Override
	public Optional<EducationData> getEducationDataById(Integer id) {
		return Optional.of(jdbcTemplate.queryForObject(sqlSingle, new BeanPropertyRowMapper<>(EducationData.class), "Education", id));
	}

	@Override
	public List<SexData> findAllSexDatas() {
		return jdbcTemplate.query(sqlAll, new BeanPropertyRowMapper<>(SexData.class), "Sex");
	}

	@Override
	public Optional<SexData> getSexDataById(Integer id) {
		return Optional.of(jdbcTemplate.queryForObject(sqlSingle, new BeanPropertyRowMapper<>(SexData.class), "Sex", id));
	}

	@Override
	public List<InterestData> finAllInterestDatas() {
		return jdbcTemplate.query(sqlAll, new BeanPropertyRowMapper<>(InterestData.class), "Interest");
	}

	@Override
	public Optional<InterestData> getInterestDataById(Integer id) {
		return Optional.of(jdbcTemplate.queryForObject(sqlSingle, new BeanPropertyRowMapper<>(InterestData.class), "Interest", id));
	}
	
}
