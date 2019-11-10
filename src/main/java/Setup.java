public class Setup {
    public static final String[] COUNTRY_CODES = {"AL", "AR", "AM", "AU", "AT", "AZ", "BD", "BE", "BA", "BR", "BZ", "BG", "CA", "CN", "CO", "CR", "DK", "EG", "GB", "EE", "FI", "FR", "GE", "DE", "GR", "HU", "IN", "IR", "IL", "IT", "JP", "KR", "MX", "MA", "NP", "NL", "NZ", "NG", "NO", "PK", "PL", "PT", "RO", "RU", "SA", "SK", "SI", "ES", "SE", "CH", "TR", "UA", "US", "VN"
    };

    public static final String BASE_URL = "http://localhost:3000/generate";

    public static String getRequestPayload(String countryCode) {
        return "{\n" +
                "  \"locale\": \""+ countryCode +"\"\n" +
                "}";
    }
}
