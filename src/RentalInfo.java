import java.util.HashMap;

public class RentalInfo {
    final String REG = "regular";
    public String statement(Customer customer) {
        HashMap<String, Movie> movies = new HashMap<>();
        movies.put("F001", new Movie("You've Got Mail", REG));
        movies.put("F002", new Movie("Matrix", REG));
        movies.put("F003", new Movie("Cars", "childrens"));
        movies.put("F004", new Movie("Fast & Furious X", "new"));

        double totalAmount = 0;
        int frequentEnterPoints = 0;
        String result = "Rental Record for " + customer.getName() + "\n";

        for (MovieRental r : customer.getRentals()) {
            double thisAmount = calculateMovieAmount(movies.get(r.getMovieId()), r.getDays());
            frequentEnterPoints += calculateFrequentPoints(movies.get(r.getMovieId()), r.getDays());

            result += "\t" + movies.get(r.getMovieId()).getTitle() + "\t" + thisAmount + "\n";
            totalAmount += thisAmount;
        }

        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentEnterPoints + " frequent points\n";

        return result;
    }

    private double calculateMovieAmount(Movie movie, int daysRented) {
        String code = movie.getCode();
        double amount = 0;

        switch (code) {
            case "regular":
                amount = 2;
                if (daysRented > 2) {
                    amount += (daysRented - 2) * 1.5;
                }
                break;
            case "new":
                amount = daysRented * 3;
                break;
            case "childrens":
                amount = 1.5;
                if (daysRented > 3) {
                    amount += (daysRented - 3) * 1.5;
                }
                break;
        }

        return amount;
    }

    private int calculateFrequentPoints(Movie movie, int daysRented) {
        if (movie.getCode().equals("new") && daysRented > 2) {
            return 2;
        }
        return 1;
    }
}
