import java.util.ArrayList;
import java.util.List;

public class Ship {
	
	private String name;

	private Port port;

	private List<Cargo> cargo = new ArrayList<>();

	public Ship(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public Port getPort() {
		return this.port;
	}

	public void handleArrival(ArrivalEvent arrivalEvt) {
		arrivalEvt.setPriorPort(this.port);
		this.port = arrivalEvt.getPort();
		for (Cargo c : cargo) {
			c.handleArrival(arrivalEvt);
		}
	}

	public void reverseArrival(ArrivalEvent arrivalEvt) {
		this.port = arrivalEvt.getPriorPort();
		for (Cargo c : cargo) {
			c.reverseArrival(arrivalEvt);
		}
	}

	public void handleDeparture(DepartureEvent departureEvt) {
		this.port = Port.AT_SEA;
	}

	public void handleLoad(LoadEvent loadEvt) {
		this.cargo.add(loadEvt.getCargo());
	}

	public void reverseLoad(LoadEvent loadEvt) {
		this.cargo.remove(loadEvt.getCargo());
	}

	public void handleUnload(UnloadEvent unloadEvt) {
		this.cargo.remove(unloadEvt.getCargo());
	}

}