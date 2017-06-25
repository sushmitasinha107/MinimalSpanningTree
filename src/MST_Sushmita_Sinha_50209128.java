import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class MST_Sushmita_Sinha_50209128 {
	static int n = 0;
	static int sum = 0;

	class Edge {
		public Integer vertex1;
		public Integer vertex2;
		public int weight;
	}
	
	public static void main(String args[]) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
		ArrayList<Object> List = new ArrayList<>();
		File file = new File("test1");
		MST_Sushmita_Sinha_50209128 mst = new MST_Sushmita_Sinha_50209128();
		Scanner sc = new Scanner(file);
		while(sc.hasNext())
		{
			n = sc.nextInt();
			int m = sc.nextInt();  //no. of edges
			for (int i = 0; i < m; i++) {
				Edge edge = mst.new Edge();
				edge.vertex1 = sc.nextInt();
				edge.vertex2 = sc.nextInt();
				edge.weight = sc.nextInt();
				List.add(edge);
			}
		
		mst = new MST_Sushmita_Sinha_50209128();
		MST_Sushmita_Sinha_50209128.Edge resultedge = mst.new Edge();
		List<Object> listEdge = mst.MST(List);
		for (Object edge : listEdge) {
			resultedge = mst.new Edge();
			resultedge = (Edge) edge;
			sum = sum + resultedge.weight;
		}
		out.println(sum);
		for (Object edge : listEdge) {
			resultedge = mst.new Edge();
			resultedge = (Edge) edge;
			out.println(resultedge.vertex1 + " " + resultedge.vertex2 + " " + resultedge.weight);
		}
		
		}
		sc.close();
		out.close();
	}


	public class BinaryMinHeap<T> {

		private List<Node> allNodes = new ArrayList<>();
		private Map<T, Integer> nodePosition = new HashMap<>();

		public class Node {
			int weight;
			T key;
		}

		
		public boolean containsData(T key) {
			return nodePosition.containsKey(key);
		}
		
		public void add(int weight, T key) {
			Node node = new Node();
			node.weight = weight;
			node.key = key;
			allNodes.add(node);
			int size = allNodes.size();
			int current = size - 1;
			int parentIndex = (current - 1) / 2;
			nodePosition.put(node.key, current);

			while (parentIndex >= 0) {
				Node parentNode = allNodes.get(parentIndex);
				Node currentNode = allNodes.get(current);
				if (parentNode.weight > currentNode.weight) {
					swap(parentNode, currentNode);
					updatePositionMap(parentNode.key, currentNode.key, parentIndex, current);
					current = parentIndex;
					parentIndex = (parentIndex - 1) / 2;
				} else {
					break;
				}
			}
		}

		
		public T min() {
			return allNodes.get(0).key;
		}
		
		public boolean empty() {
			return allNodes.size() == 0;
		}
	
		public void decrease(T data, int reducedWeight) {
			Integer position = nodePosition.get(data);
			allNodes.get(position).weight = reducedWeight;
			int parent = (position - 1) / 2;
			while (parent >= 0) {
				if (allNodes.get(parent).weight > allNodes.get(position).weight) {
					swap(allNodes.get(parent), allNodes.get(position));
					updatePositionMap(allNodes.get(parent).key, allNodes.get(position).key, parent, position);
					position = parent;
					parent = (parent - 1) / 2;
				} else {
					break;
				}
			}
		}

		public Integer getWeight(T key) {
			Integer position = nodePosition.get(key);
			if (position == null) {
				return null;
			} else {
				return allNodes.get(position).weight;
			}
		}
		
		public int extractMinNode() {
			int size = allNodes.size() - 1;
			Node minNode = new Node();
			minNode.key = allNodes.get(0).key;
			minNode.weight = allNodes.get(0).weight;

			int lastNodeWeight = allNodes.get(size).weight;
			allNodes.get(0).weight = lastNodeWeight;
			allNodes.get(0).key = allNodes.get(size).key;
			nodePosition.remove(minNode.key);
			nodePosition.remove(allNodes.get(0));
			nodePosition.put(allNodes.get(0).key, 0);
			allNodes.remove(size);

			int currentIndex = 0;
			size--;
			while (true) {
				int left = 2 * currentIndex + 1;
				int right = 2 * currentIndex + 2;
				if (left > size) {
					break;
				}
				if (right > size) {
					right = left;
				}
				int smallerIndex = allNodes.get(left).weight <= allNodes.get(right).weight ? left : right;
				if (allNodes.get(currentIndex).weight > allNodes.get(smallerIndex).weight) {
					swap(allNodes.get(currentIndex), allNodes.get(smallerIndex));
					updatePositionMap(allNodes.get(currentIndex).key, allNodes.get(smallerIndex).key, currentIndex,
							smallerIndex);
					currentIndex = smallerIndex;
				} else {
					break;
				}
			}
			return (int) minNode.key;
		}
		

		private void swap(Node node1, Node node2) {
			int weight = node1.weight;
			T data = node1.key;

			node1.key = node2.key;
			node1.weight = node2.weight;

			node2.key = data;
			node2.weight = weight;
		}

		private void updatePositionMap(T data1, T data2, int pos1, int pos2) {
			nodePosition.remove(data1);
			nodePosition.remove(data2);
			nodePosition.put(data1, pos1);
			nodePosition.put(data2, pos2);
		}

	
	}

	public List<Object> MST(ArrayList<Object> list) {
		Map<Integer, Object> vertexToEdge = new HashMap<Integer, Object>();
		BinaryMinHeap<Integer> minHeap = new BinaryMinHeap<Integer>();
		List<Object> result = new ArrayList<Object>();
		for (int i = 1; i <= n; i++) {
			minHeap.add(Integer.MAX_VALUE, i);
		}

		minHeap.decrease(1, 0);
		while (!minHeap.empty()) {
			// extract min weight vertex from heap
			int current = minHeap.extractMinNode();
			MST_Sushmita_Sinha_50209128 mst1;
			if (current != 1) {
				Object spanningTreeEdge = vertexToEdge.get(current);
				if (spanningTreeEdge != null) {
					result.add(spanningTreeEdge);
				}
			}

			for (Object edge : list) {
				mst1 = new MST_Sushmita_Sinha_50209128();
				MST_Sushmita_Sinha_50209128.Edge adjacentEdge = mst1.new Edge();
				adjacentEdge = (Edge) edge;
				if (adjacentEdge.vertex1 == current) {
					if (minHeap.containsData(adjacentEdge.vertex2)
							&& minHeap.getWeight(adjacentEdge.vertex2) > adjacentEdge.weight) {
						minHeap.decrease(adjacentEdge.vertex2, adjacentEdge.weight);
						vertexToEdge.put(adjacentEdge.vertex2, edge);
					}
				}
				if (adjacentEdge.vertex2 == current) {
					if (minHeap.containsData(adjacentEdge.vertex1)
							&& minHeap.getWeight(adjacentEdge.vertex1) > adjacentEdge.weight) {
						// assign reduced value
						minHeap.decrease(adjacentEdge.vertex1, adjacentEdge.weight);

						vertexToEdge.put(adjacentEdge.vertex1, edge);

					}
				}
			}
		}
		return result;
	}
}
