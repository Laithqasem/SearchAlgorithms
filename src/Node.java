import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Node {
    private String city;

    private final ArrayList<SubNode> connectedCities;
    private final ArrayList<SubNode> allCities;

    public Node() {
        connectedCities = new ArrayList<SubNode>();
        allCities = new ArrayList<SubNode>();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<SubNode> getConnectedCities() {
        Collections.sort(connectedCities, new Comparator<SubNode>() {
            @Override
            public int compare(SubNode o1, SubNode o2) {
                return o1.getCity().compareTo(o2.getCity());
            }
        });


        return connectedCities;
    }

    public ArrayList<SubNode> getAllCities() {
        Collections.sort(allCities, new Comparator<SubNode>() {
            @Override
            public int compare(SubNode o1, SubNode o2) {
                return o1.getCity().compareTo(o2.getCity());
            }
        });


        return allCities;
    }

    public void printConnectedCities() {
        Collections.sort(connectedCities, new Comparator<SubNode>() {
            @Override
            public int compare(SubNode o1, SubNode o2) {
                return o1.getCity().compareTo(o2.getCity());
            }
        });
        for (SubNode node : connectedCities) {
            System.out.println(node.getCity());
            System.out.println(node.getAirialDistance());
            System.out.println(node.getCarDistance());
            System.out.println(node.getWalkDistance());


        }
    }

    public void printAllCities() {
        Collections.sort(allCities, new Comparator<SubNode>() {
            @Override
            public int compare(SubNode o1, SubNode o2) {
                return o1.getCity().compareTo(o2.getCity());
            }
        });

        for (SubNode node : allCities) {
            System.out.println(node.getCity());
            System.out.println(node.getAirialDistance());
            System.out.println(node.getWalkDistance());
        }
    }

    public void addToConnectedCities(SubNode city) {
        if (city.getCarDistance() != 0)
            this.connectedCities.add(city);
        this.allCities.add(city);


        Collections.sort(connectedCities, new Comparator<SubNode>() {
            @Override
            public int compare(SubNode o1, SubNode o2) {
                return o1.getCity().compareTo(o2.getCity());
            }
        });
    }

    public SubNode getCitByName(String name) {
        Collections.sort(allCities, new Comparator<SubNode>() {
            @Override
            public int compare(SubNode o1, SubNode o2) {
                return o1.getCity().compareTo(o2.getCity());
            }
        });


        for (SubNode node : allCities) {
            if (node.getCity().equals(name))
                return node;
        }
        return null;
    }

    public SubNode getConnectedCityByName(String name) {
        Collections.sort(connectedCities, new Comparator<SubNode>() {
            @Override
            public int compare(SubNode o1, SubNode o2) {
                return o1.getCity().compareTo(o2.getCity());
            }
        });

        for (SubNode node : connectedCities) {
            if (node.getCity().equals(name))
                return node;
        }
        return null;
    }
}
