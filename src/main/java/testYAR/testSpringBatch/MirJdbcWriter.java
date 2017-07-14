package testYAR.testSpringBatch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import common.Constantes;
import testYAR.testSpringBatch.model.bd.TrimestreHorsCipav;

/**
 * ItemWriter
 * 
 * @author yarrami
 *
 */
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class MirJdbcWriter implements ItemWriter<TrimestreHorsCipav> {

	private JdbcTemplate jdbcTemplate;
	private static final String REQUEST_INSERT = "insert into trimestre_hors_cipav (numCarriere, annee, natureHorsCipav, regimeExterne, typeTrimestre, nombreTrimestre, organismeDeclarant, dateDeclaration) values (?,?,?,?,?,?,?,?)";

	public void write(List<? extends TrimestreHorsCipav> items) throws Exception {
		for (TrimestreHorsCipav trimestre : items) {
			final Object object[] = { trimestre.getNumCarriere(), trimestre.getAnnee(), trimestre.getNatureHorsCipav(), trimestre.getRegimeExterne(), trimestre.getTypeTrimestre(),
					trimestre.getNombreTrimestre(), trimestre.getOrganismeDeclarant(), trimestre.getDateDeclaration() };
			jdbcTemplate.update(REQUEST_INSERT, object);
			Constantes.OKlog.info(trimestre.getLigneTotal());
		}
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
