package testYAR.testSpringBatch;


import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import testYAR.testSpringBatch.model.SngcDdf;
import testYAR.testSpringBatch.model.SngcMirTrim;

@Transactional (propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MirJdbcWriter implements ItemWriter<SngcMirTrim>{

    private JdbcTemplate jdbcTemplate;
    private static final String REQUEST_INSERT_MIR = "insert into MIR (type_enregistrement, zone_id_assure, zone_partenaire, zone_trimestres_regime, zone_anomalies_retours) values (?,?,?,?,?)";

    public void write(List<? extends SngcMirTrim> items) throws Exception {
        for (SngcMirTrim sngcMir : items) {
            final Object object [] = { sngcMir.getTypeEnregistrement(), sngcMir.getZoneIdAssure(), sngcMir.getZonePartenaire(), sngcMir.getZoneTrimestresRegime(), sngcMir.getZoneAnomaliesRetours() };
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
