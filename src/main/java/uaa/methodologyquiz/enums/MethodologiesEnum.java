package uaa.methodologyquiz.enums;

import uaa.methodologyquiz.classes.Methodology;
import static uaa.methodologyquiz.datagen.MethodologyGenerator.generateMethodology;

/**
 *
 * @author root
 */
public enum MethodologiesEnum {
    AM,
    ASD,
    AUP,
    CAF,
    CAS,
    CASINC,
    CASPROT,
    CASSUB,
    COTS,
    CRYSTAL,
    DAD,
    DESBASCOMP,
    DEVOPS,
    DISHER,
    DPP,
    DSDM,
    ENTEVO,
    ENTPET,
    ESP,
    ESPRR,
    FDD,
    KANBAN,
    LSD,
    MELE,
    MODINC,
    MODITE,
    MV,
    OOAD,
    OPEN,
    PROT,
    PROTEVO,
    PUDS,
    RAD,
    SAS,
    SCRUM,
    SCRUMBAN,
    TDP,
    XP,
    ZAVE;
    
    public Methodology methodology() {
        return generateMethodology(this);
    }
}
