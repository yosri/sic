package testYAR.testSpringBatch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import testYAR.testSpringBatch.model.bd.TrimestreHorsCipav;

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class MirJdbcWriter implements ItemWriter<TrimestreHorsCipav> {

	private JdbcTemplate jdbcTemplate;
	private static final String REQUEST_INSERT_MIR = "insert into MIR (type_enregistrement, zone_id_assure, zone_partenaire, zone_trimestres_regime, zone_anomalies_retours) values (?,?,?,?,?)";

	public void write(List<? extends TrimestreHorsCipav> items) throws Exception {
		for (TrimestreHorsCipav trimestre : items) {
			final Object object[] = { trimestre.getNumCarriere(), trimestre.getOrganismeDeclarant(), trimestre.getNatureHorsCipav(), trimestre.getRegimeExterne(),
					trimestre.getTypeTrimestre() };
			jdbcTemplate.update(REQUEST_INSERT_MIR, object);
		}
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
