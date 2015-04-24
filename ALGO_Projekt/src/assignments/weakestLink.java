package assignments;

	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.HashMap;
	import java.util.HashSet;
	import java.util.LinkedList;
	import java.util.List;
	import java.util.Map;
	import java.util.Set;


	public class weakestLink {

	  private final List<Person> nodes;
	  private final List<Edges> edges;
	  private Set<Person> settledNodes;
	  private Set<Person> unSettledNodes;
	  private Map<Person, Person> predecessors;
	  private Map<Person, Integer> distance;

	  public weakestLink(Graph graph) {
	    // create a copy of the array so that we can operate on this array
	    this.nodes = new ArrayList<Person>(graph.getVertexes());
	    this.edges = new ArrayList<Edges>(graph.getEdges());
	  }

	  public void execute(Person source) {
	    settledNodes = new HashSet<Person>();
	    unSettledNodes = new HashSet<Person>();
	    distance = new HashMap<Person, Integer>();
	    predecessors = new HashMap<Person, Person>();
	    distance.put(source, 0);
	    unSettledNodes.add(source);
	    while (unSettledNodes.size() > 0) {
	      Person node = getMinimum(unSettledNodes);
	      settledNodes.add(node);
	      unSettledNodes.remove(node);
	      findMinimalDistances(node);
	    }
	  }

	  private void findMinimalDistances(Person node) {
	    List<Person> adjacentNodes = getNeighbors(node);
	    for (Person target : adjacentNodes) {
	      if (getShortestDistance(target) > getShortestDistance(node)
	          + getDistance(node, target)) {
	        distance.put(target, getShortestDistance(node)
	            + getDistance(node, target));
	        predecessors.put(target, node);
	        unSettledNodes.add(target);
	      }
	    }

	  }

	  private int getDistance(Person node, Person target) {
	    for (Edges edge : edges) {
	      if (edge.getSource().equals(node)
	          && edge.getDestination().equals(target)) {
	        return edge.getWeight();
	      }
	    }
	    throw new RuntimeException("Should not happen");
	  }

	  private List<Person> getNeighbors(Person node) {
	    List<Person> neighbors = new ArrayList<Person>();
	    for (Edges edge : edges) {
	      if (edge.getSource().equals(node)
	          && !isSettled(edge.getDestination())) {
	        neighbors.add(edge.getDestination());
	      }
	    }
	    return neighbors;
	  }

	  private Person getMinimum(Set<Person> vertexes) {
	    Person minimum = null;
	    for (Person Person : vertexes) {
	      if (minimum == null) {
	        minimum = Person;
	      } else {
	        if (getShortestDistance(Person) < getShortestDistance(minimum)) {
	          minimum = Person;
	        }
	      }
	    }
	    return minimum;
	  }

	  private boolean isSettled(Person vertex) {
	    return settledNodes.contains(vertex);
	  }

	  private int getShortestDistance(Person destination) {
	    Integer d = distance.get(destination);
	    if (d == null) {
	      return Integer.MAX_VALUE;
	    } else {
	      return d;
	    }
	  }

	  /*
	   * This method returns the path from the source to the selected target and
	   * NULL if no path exists
	   */
	  public LinkedList<Person> getPath(Person target) {
	    LinkedList<Person> path = new LinkedList<Person>();
	    Person step = target;
	    // check if a path exists
	    if (predecessors.get(step) == null) {
	      return null;
	    }
	    path.add(step);
	    while (predecessors.get(step) != null) {
	      step = predecessors.get(step);
	      path.add(step);
	    }
	    // Put it into the correct order
	    Collections.reverse(path);
	    return path;
	  }

	 

	

	public class Person {
		  final private String id;
		  final private String name;
		  
		  
		  public Person(String id, String name) {
		    this.id = id;
		    this.name = name;
		  }
		  public String getId() {
		    return id;
		  }

		  public String getName() {
		    return name;
		  }
		  
		  @Override
		  public int hashCode() {
		    final int prime = 31;
		    int result = 1;
		    result = prime * result + ((id == null) ? 0 : id.hashCode());
		    return result;
		  }
		  
		  @Override
		  public boolean equals(Object obj) {
		    if (this == obj)
		      return true;
		    if (obj == null)
		      return false;
		    if (getClass() != obj.getClass())
		      return false;
		    Person other = (Person) obj;
		    if (id == null) {
		      if (other.id != null)
		        return false;
		    } else if (!id.equals(other.id))
		      return false;
		    return true;
		  }

		  @Override
		  public String toString() {
		    return name;
		  }
		  
		} 
	
	public class Edges  {
		  private final String id; 
		  private final Person source;
		  private final Person destination;
		  private final int weight; 
		  
		  public Edges(String id, Person source, Person destination, int weight) {
		    this.id = id;
		    this.source = source;
		    this.destination = destination;
		    this.weight = weight;
		  }
		  
		  public String getId() {
		    return id;
		  }
		  public Person getDestination() {
		    return destination;
		  }

		  public Person getSource() {
		    return source;
		  }
		  public int getWeight() {
		    return weight;
		  }
		  
		  @Override
		  public String toString() {
		    return source + " " + destination;
		  }
		  
		  
		} 

	public class Graph {
	  private final List<Person> vertexes;
	  private final List<Edges> edges;

	  public Graph(List<Person> vertexes, List<Edges> edges) {
	    this.vertexes = vertexes;
	    this.edges = edges;
	  }

	  public List<Person> getVertexes() {
	    return vertexes;
	  }

	  public List<Edges> getEdges() {
	    return edges;
	  }
	  
	  
	  
	} 
	}



