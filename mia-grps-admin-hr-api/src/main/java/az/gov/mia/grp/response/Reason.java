package az.gov.mia.grp.response;

/**
 * @author Khayal Farziyev
 */
public enum Reason {

//    SUCCESS_ADD("Item successfully added"),
//    SUCCESS_DELETE("Item successfully deleted"),
//    SUCCESS_GET("Item successfully got"),
//    SUCCESS_UPDATE("Item successfully updated"),
//    NOT_FOUND("This item not founded"),
//    ALREADY_EXIST("This item already exist in db"),
//    DUPLICATE_VALUE("Duplicate item value "),
//    NOTHING_IN_CHANGE("Nothing in change"),
//    FAILURE_GET("Not founded.");


    SUCCESS_ADD("Data əlavə olundu"),
    SUCCESS_DELETE("Data silindi"),
    SUCCESS_GET("Data əldə olundu"),
    SUCCESS_UPDATE("Data yeniləndi"),
    SUCCESS_CALCULATE("Data hesablandı"),
    NOT_FOUND("Data tapılmadı"),
    ALREADY_EXIST("Data artıq bazada mövcuddur"),
    DUPLICATE_VALUE("Duplicate item value "),
    NOTHING_IN_CHANGE("Nothing in change"),
    FAILURE_GET("Not founded."),
    UNKNOW("Xəta baş verdi"),
    NAMENULL("Boş xanaları doldurun"),
    FORBIDDEN("Bu əməliyyatı etməyə hüququnuz çatmır"),
    CONSTRAINT_VIOLATED("Məlumatın digər cədvəllə əlaqəsi var"),
    CONSTRAINT_SUB("Qurumun alt qurumları var"),
    SUCCESSFUL_OPERATION("Uğurlu əməliyyat"),
    IS_EMPTY("List boşdur"),
    ROLE_DATA_INTEGRITY("İlk öncə rolun aid olduğu istifadəçilərin rolunu dəyişin."),
    DATE_TIME_PARSE("Tarix formatı düzgün deyil"),
    NUMBER_FORMAT("Yalnız rəqəm daxil edilməlidir"),
    ISCORRET_PARAMETR("Parametr uyğun formatda deyil");


    private final String value;

    Reason(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
