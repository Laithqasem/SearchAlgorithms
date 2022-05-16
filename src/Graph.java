import java.util.*;

public class Graph {
    private ArrayList<Node> cities;
    private Node start;
    private SubNode goal;

    int pathCostForAerial;
    int pathCostForWalk;
    int pathCostForCar;
    public Graph() {
        cities = new ArrayList<Node>();
    }

    public ArrayList<Node> getCities() {
        return cities;
    }

    public void printCities() {
        for (Node node : cities) {
            System.out.println(node.getCity());
        }
    }

    public void addToCities(Node city) {
        this.cities.add(city);
    }

    public Node getCitByName(String name) {
        for (Node node : cities) {
            if (node.getCity().equals(name))
                return node;
        }
        return null;
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public SubNode getGoal() {
        return goal;
    }

    public void setGoal(SubNode goal) {
        this.goal = goal;
    }

    public void BFS() {
        ArrayList<String> visited = new ArrayList<String>();
        ArrayList<String> path = new ArrayList<String>();

        ArrayList<Node> list = new ArrayList<Node>();
        list.add(start);
        int t = 0;
        while (!list.isEmpty()) {
            Node temp = list.remove(0);
            ArrayList<SubNode> nodes = temp.getConnectedCities();
            for (SubNode subNode : nodes) {
                if (subNode.getCity().equals(goal.getCity())) {
                    t = 1;
                    break;
                }

                if (!list.contains(getCitByName(subNode.getCity())))
                    list.add(getCitByName(subNode.getCity()));
            }
            visited.add(temp.getCity());
            if (t == 1)
                break;
        }
        boolean hold = true;
        path.add(goal.getCity());
        String g = goal.getCity();
        while (hold) {
            for (String city : visited) {

                if (getCitByName(city).getConnectedCityByName(g) != null) {
                    path.add(city);

                    pathCostForAerial+=getCitByName(city).getConnectedCityByName(g).getAirialDistance();
                    pathCostForWalk+=getCitByName(city).getConnectedCityByName(g).getWalkDistance();
                    pathCostForCar+=getCitByName(city).getConnectedCityByName(g).getCarDistance();

                    g = city;
                    if (city.equals(start.getCity())) {
                        hold = false;
                        break;
                    }
                    break;
                }
            }
        }
        visited.add(goal.getCity());
        Collections.reverse(path);

        System.out.print("BFS visited nodes: ");
        for (String s : visited) {
            if (s.equals(visited.get(visited.size()-1))){
                System.out.print(s );

            }
            else{System.out.print(s + " -> ");
            }}
        System.out.println();
        System.out.print("BFS path: ");
        for (String s : path) {
            if (s.equals(path.get(path.size()-1))){
                System.out.print(s );

            }
            else{System.out.print(s + " -> ");
            }}

        System.out.println();
        System.out.println("Path Cost For Walking: "+pathCostForWalk);
        System.out.println("Path Cost For Car: "+pathCostForCar);
        System.out.println("Path Cost For Aerial: "+pathCostForAerial);
        System.out.println("---------------------------------------------------");
    }


    public void IterativeDFS() {
        ArrayList<String> visited = new ArrayList<String>();
        ArrayList<String> path = new ArrayList<String>();

        Stack<Node> stack = new Stack<>();
        stack.push(start);

        while (!stack.empty()) {
            Node temp = stack.peek();
            stack.pop();

            if (!visited.contains(temp.getCity())) {
                visited.add(temp.getCity());
            } else
                continue;

            ArrayList<SubNode> nodes = temp.getConnectedCities();
            for (SubNode subNode : nodes) {
                if (!visited.contains(subNode.getCity())) {
                    stack.push(getCitByName(subNode.getCity()));
                }

            }
        }

        boolean hold = true;
        path.add(goal.getCity());
        String g = goal.getCity();
        while (hold) {

            for (String city : visited) {

                if (getCitByName(city).getConnectedCityByName(g) != null) {
                    path.add(city);
                    pathCostForAerial+=getCitByName(city).getConnectedCityByName(g).getAirialDistance();
                    pathCostForWalk+=getCitByName(city).getConnectedCityByName(g).getWalkDistance();
                    pathCostForCar+=getCitByName(city).getConnectedCityByName(g).getCarDistance();

                    g = city;
                    if (city.equals(start.getCity())) {
                        hold = false;
                        break;
                    }
                    break;
                }
            }
        }
        Collections.reverse(path);

        System.out.print("Iterative DFS visited nodes: ");
        for (String s : visited) {
            if (s.equals(visited.get(visited.size()-1))){
                System.out.print(s );

            }
            else{System.out.print(s + " -> ");
            }}
        System.out.println();
        System.out.print("Iterative DFS path: ");
        for (String s : path) {
            if (s.equals(path.get(path.size()-1))){
                System.out.print(s );

            }
            else{System.out.print(s + " -> ");
            }}
        System.out.println();
        System.out.println("Path Cost For Walking: "+pathCostForWalk);
        System.out.println("Path Cost For Car: "+pathCostForCar);
        System.out.println("Path Cost For Aerial: "+pathCostForAerial);
        System.out.println("---------------------------------------------------");
    }

    public void DFS() {
        Stack<Node> stack = new Stack<Node>();
        ArrayList<String> visited = new ArrayList<String>();
        ArrayList<String> array = new ArrayList<String>();
        ArrayList<String> path = new ArrayList<String>();
        stack.push(start);
        boolean flag = true;
        while (!stack.isEmpty()) {

            Node current = stack.pop();
            if (!visited.contains(current.getCity())) {
                visited.add(current.getCity());
                ArrayList<SubNode> s = current.getConnectedCities();
//                Collections.reverse(s);
                for (SubNode dest : s) {
                    if (dest.getCity().contains(goal.getCity())){

                        array.add(dest.getCity());
                      flag=false;

                      break;

                    }


                    if (!visited.contains(dest.getCity()))
                        array.add(dest.getCity());
//                        stack.push(getCitByName(dest.getCity()));
                }
                while (!array.isEmpty()){
                    stack.push(getCitByName(array.get(array.size()-1)));
                    array.remove(array.size()-1);
                }
                if (visited.contains(goal.getCity())){
                    break;

                }
            }
        }

        System.out.print("DFS visited nodes: ");
        for (String s : visited) {
            if (s.equals(visited.get(visited.size()-1))){
                System.out.print(s );

            }
            else{System.out.print(s + " -> ");
            }}


        boolean hold = true;
        path.add(goal.getCity());
        String g = goal.getCity();
        Collections.reverse(visited);
        visited.remove(0);
        while (hold) {

            for (String city : visited) {

                if (getCitByName(city).getConnectedCityByName(g) != null) {
                    path.add(city);
                    pathCostForAerial+=getCitByName(city).getConnectedCityByName(g).getAirialDistance();
                    pathCostForWalk+=getCitByName(city).getConnectedCityByName(g).getWalkDistance();
                    pathCostForCar+=getCitByName(city).getConnectedCityByName(g).getCarDistance();

                    g = city;
                    if (city.equals(start.getCity())) {
                        hold = false;
                        break;
                    }

                }
            }
        }
        Collections.reverse(path);
        System.out.println();
        System.out.println();

        System.out.print(" DFS path: ");
        int pathCost;
        for (String s : path) {
            if (s.equals(path.get(path.size()-1))){
                System.out.print(s );

            }
            else{System.out.print(s + " -> ");
            }}  System.out.println();  System.out.println();


        System.out.println();
        System.out.println("Path Cost For Walking: "+pathCostForWalk);
        System.out.println("Path Cost For Car: "+pathCostForCar);
        System.out.println("Path Cost For Aerial: "+pathCostForAerial);
        System.out.println("---------------------------------------------------");
    }

    public void  aStar(){
      Map<Node,Integer> mp=new HashMap<Node,Integer>();
        ArrayList<String> visited = new ArrayList<String>();
        ArrayList<String> path = new ArrayList<String>();

      Node current = start;
        visited.add(start.getCity());
      while (!visited.contains(goal.getCity())){
          for (SubNode s:
               current.getConnectedCities()) {
              if(!visited.contains(s.getCity()))
                mp.put(getCitByName(s.getCity()),s.getAirialDistance()+s.getWalkDistance());

          }
          int min = Collections.min(mp.values());

          current = getKey(mp, min);
          mp.remove(current);

          visited.add(current.getCity());

      }

        System.out.print("A* visited nodes: ");
        for (String s : visited) {
            if (s.equals(visited.get(visited.size()-1))){
                System.out.print(s );

            }
            else{System.out.print(s + " -> ");
            }}


        boolean hold = true;
        path.add(goal.getCity());
        String g = goal.getCity();
        Collections.reverse(visited);
        visited.remove(0);
        while (hold) {

            for (String city : visited) {

                if (getCitByName(city).getConnectedCityByName(g) != null) {
                    path.add(city);
                    pathCostForAerial+=getCitByName(city).getConnectedCityByName(g).getAirialDistance();
                    pathCostForWalk+=getCitByName(city).getConnectedCityByName(g).getWalkDistance();
                    pathCostForCar+=getCitByName(city).getConnectedCityByName(g).getCarDistance();

                    g = city;
                    if (city.equals(start.getCity())) {
                        hold = false;
                        break;
                    }
//                    break;
                }
            }
        }
        Collections.reverse(path);
        System.out.println();
        System.out.println();

        System.out.print(" A* path: ");
        for (String s : path) {
            if (s.equals(path.get(path.size()-1))){
                System.out.print(s );

            }
            else{System.out.print(s + " -> ");
            }}
        System.out.println();
        System.out.println("Path Cost For Walking: "+pathCostForWalk);
        System.out.println("Path Cost For Car: "+pathCostForCar);
        System.out.println("Path Cost For Aerial: "+pathCostForAerial);
        System.out.println("---------------------------------------------------");


    }

    public void  aStar2(){
        Map<Node,Integer> mp=new HashMap<Node,Integer>();
        ArrayList<String> visited = new ArrayList<String>();
        ArrayList<String> path = new ArrayList<String>();

        Node current = start;
        visited.add(start.getCity());
        while (!visited.contains(goal.getCity())){
            for (SubNode s:
                    current.getConnectedCities()) {
                if(!visited.contains(s.getCity()))

                    mp.put(getCitByName(s.getCity()),s.getCarDistance()+s.getWalkDistance());

            }
            int min = Collections.min(mp.values());

            current = getKey(mp, min);
            mp.remove(current);

            visited.add(current.getCity());
        }

        System.out.print("A* visited nodes: ");
        for (String s : visited) {
            if (s.equals(visited.get(visited.size()-1))){
                System.out.print(s );

            }
            else{System.out.print(s + " -> ");
            }}


        boolean hold = true;
        path.add(goal.getCity());
        String g = goal.getCity();
        Collections.reverse(visited);
        visited.remove(0);
        int pathCost = 0;
        while (hold) {

            for (String city : visited) {

                if (getCitByName(city).getConnectedCityByName(g) != null) {
                    path.add(city);
                    pathCostForAerial+=getCitByName(city).getConnectedCityByName(g).getAirialDistance();
                    pathCostForWalk+=getCitByName(city).getConnectedCityByName(g).getWalkDistance();
                    pathCostForCar+=getCitByName(city).getConnectedCityByName(g).getCarDistance();g = city;
                    if (city.equals(start.getCity())) {
                        hold = false;
                        break;
                    }

                }
            }
        }
        Collections.reverse(path);
        System.out.println();
        System.out.println();

        System.out.print(" A* path: ");
        for (String s : path) {
            if (s.equals(path.get(path.size()-1))){
                System.out.print(s );

            }
            else{System.out.print(s + " -> ");
            }}
        System.out.println();
        System.out.println("Path Cost For Walking: "+pathCostForWalk);
        System.out.println("Path Cost For Car: "+pathCostForCar);
        System.out.println("Path Cost For Aerial: "+pathCostForAerial);
        System.out.println("---------------------------------------------------");

    }

    public void  Greedy(int route){
        Map<Node,Integer> mp=new HashMap<Node,Integer>();
        ArrayList<String> visited = new ArrayList<String>();
        ArrayList<String> path = new ArrayList<String>();


        Node current = start;
        visited.add(start.getCity());
        while (!visited.contains(goal.getCity())){
            for (SubNode s:
                    current.getConnectedCities()) {
                if(!visited.contains(s.getCity())) {
                    if (route == 3) {
                        mp.put(getCitByName(s.getCity()), s.getAirialDistance());
                    }
                    if (route == 2) {
                        mp.put(getCitByName(s.getCity()), s.getCarDistance());
                    }
                    if (route == 1) {
                        mp.put(getCitByName(s.getCity()), s.getWalkDistance());
                    }
                }

            }
            int min = Collections.min(mp.values());

            current = getKey(mp, min);
            mp.remove(current);

            visited.add(current.getCity());
        }

        System.out.print("Greedy visited nodes: ");
        for (String s : visited) {
            if (s.equals(visited.get(visited.size()-1))){
                System.out.print(s );

            }
            else{System.out.print(s + " -> ");
        }}


        boolean hold = true;
        path.add(goal.getCity());
        String g = goal.getCity();
        Collections.reverse(visited);
        visited.remove(0);
        while (hold) {

            for (String city : visited) {

                if (getCitByName(city).getConnectedCityByName(g) != null) {
                    path.add(city);
                    pathCostForAerial+=getCitByName(city).getConnectedCityByName(g).getAirialDistance();
                    pathCostForWalk+=getCitByName(city).getConnectedCityByName(g).getWalkDistance();
                    pathCostForCar+=getCitByName(city).getConnectedCityByName(g).getCarDistance();

                    g = city;
                    if (city.equals(start.getCity())) {
                        hold = false;
                        break;
                    }
//                    break;
                }
            }
        }
        Collections.reverse(path);
        System.out.println();
        System.out.println();

        System.out.print(" Greedy path: ");
        for (String s : path) {
            if (s.equals(path.get(path.size()-1))){
                System.out.print(s );

            }
            else{System.out.print(s + " -> ");
            }}

        System.out.println();
        System.out.println("Path Cost For Walking: "+pathCostForWalk);
        System.out.println("Path Cost For Car: "+pathCostForCar);
        System.out.println("Path Cost For Aerial: "+pathCostForAerial);
        System.out.println("---------------------------------------------------");
    }
    public  <K, V> K getKey(Map<K, V> map, V value)
    {
        for (Map.Entry<K, V> entry: map.entrySet())
        {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }


}