package persistence;

// A reader that can read recipe data from a file
public class Reader {
    public static final String DELIMITER = ",";
    public static final String END = "; ";


   /* // EFFECTS: returns a list of recipes parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<Recipe> readRecipes(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of accounts parsed from list of strings
    // where each string contains data for one account
    private static List<Dish> parseContent(List<String> fileContent) {
        List<Dish> dishes = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            dishes.add(parseAccount(lineComponents));
        }

        return dishes;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 4 where element 0 represents the
    // id of the next account to be constructed, element 1 represents
    // the id, elements 2 represents the name and element 3 represents
    // the balance of the account to be constructed
    // EFFECTS: returns an account constructed from components
    private static Account parseAccount(List<String> components) {
        int nextId = Integer.parseInt(components.get(0));
        int id = Integer.parseInt(components.get(1));
        String name = components.get(2);
        double balance = Double.parseDouble(components.get(3));
        return new Account(nextId, id, name, balance);
    }*/
}