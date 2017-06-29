package bunnyWars.main;

import java.util.*;

public class BunnyWarsStructure implements IBunnyWarsStructure {

    private static final int TeamCount = 5;
    TreeSet<Integer> ids;
    HashMap<Integer, HashMap<Integer, HashSet<Bunny>>> roomsById;
    HashMap<String, Bunny> bunnies;
    TreeMap<String, Bunny> reversedNameBunnies;
    HashMap<Integer, TreeMap<String, Bunny>> bunniesByTeam;

    public BunnyWarsStructure() {
        this.ids = new TreeSet<>();
        this.roomsById = new HashMap<>();
        this.bunnies = new HashMap<>();
        this.reversedNameBunnies = new TreeMap<>();
        this.bunniesByTeam = new HashMap<>();
    }

    @Override
    public int getBunnyCount() {
        return this.bunnies.size();
    }

    @Override
    public int getRoomCount() {
        return this.roomsById.size();
    }

    @Override
    public void addRoom(int roomId) {
        if (this.ids.contains(roomId)){
            throw new IllegalArgumentException();
        }
        this.ids.add(roomId);
        this.roomsById.put(roomId, new HashMap<>());
    }

    @Override
    public void addBunny(String name, int team, int roomId) {
        if(team < 0 || team > 4){
            throw new IndexOutOfBoundsException();
        }
        if (!this.ids.contains(roomId) || this.bunnies.containsKey(name)){
            throw new IllegalArgumentException();
        }
        HashMap<Integer, HashSet<Bunny>> room = this.roomsById.get(roomId);

        Bunny bunny = new Bunny(name,team, roomId);
        this.bunnies.put(name, bunny);

        StringBuilder sb = new StringBuilder(name);
        sb.reverse();
        this.reversedNameBunnies.put(sb.toString(), bunny);
        TreeMap<String,Bunny> set = new TreeMap<>(Comparator.reverseOrder());
        if(bunniesByTeam.containsKey(team)){
            set = bunniesByTeam.get(team);
        }
        set.put(name, bunny);
        this.bunniesByTeam.put(team, set);

        if (room.isEmpty() || room.get(team) == null ){
            HashSet<Bunny> newSet = new HashSet<>();
            newSet.add(bunny);
            room.put(team, newSet);
        }
        else {
            room.get(team).add(bunny);
        }
        bunny.setRoomId(roomId);
    }

    @Override
    public void remove(int roomId) {
        if (!this.ids.contains(roomId)){
            throw new IllegalArgumentException();
        }
        HashMap<Integer, HashSet<Bunny>> room = this.roomsById.get(roomId);
        for(HashSet<Bunny> team : room.values()){
            for(Bunny bunny : team){
                bunnies.remove(bunny.getName());

                StringBuilder sb = new StringBuilder(bunny.getName());
                sb.reverse();

                reversedNameBunnies.remove(sb.toString());
                bunniesByTeam.get(bunny.getTeam()).remove(bunny.getName());
            }
        }
        this.roomsById.remove(roomId);
    }

    @Override
    public void next(String bunnyName) {
        if (!this.bunnies.containsKey(bunnyName)){
            throw new IllegalArgumentException();
        }

        int roomId = this.bunnies.get(bunnyName).getRoomId();
        Integer nextRoomId = ids.ceiling(roomId+1);
        if(nextRoomId == null) {
            nextRoomId = this.ids.first();
        }
        HashMap<Integer, HashSet<Bunny>> room = this.roomsById.get(roomId);
        Map<Integer, HashSet<Bunny>> nextRoom = this.roomsById.get(nextRoomId);

        Bunny movedBunny = bunnies.get(bunnyName);
        movedBunny.setRoomId(nextRoomId);
        room.get(movedBunny.getTeam()).remove(movedBunny);

        if(!nextRoom.containsKey(movedBunny.getTeam())){
            nextRoom.put(movedBunny.getTeam(),  new HashSet<>());
        }
        nextRoom.get(movedBunny.getTeam()).add(movedBunny);
    }

    @Override
    public void previous(String bunnyName) {
        if (!this.bunnies.containsKey(bunnyName)){
            throw new IllegalArgumentException();
        }

        int roomId = this.bunnies.get(bunnyName).getRoomId();
        Integer prevRoomId = ids.floor(roomId-1);
        if(prevRoomId == null) {
            prevRoomId = this.ids.last();
        }
        HashMap<Integer, HashSet<Bunny>> room = this.roomsById.get(roomId);
        Map<Integer, HashSet<Bunny>> prevRoom = this.roomsById.get(prevRoomId);

        Bunny movedBunny = bunnies.get(bunnyName);
        movedBunny.setRoomId(prevRoomId);
        room.get(movedBunny.getTeam()).remove(movedBunny);

        if(!prevRoom.containsKey(movedBunny.getTeam())){
            prevRoom.put(movedBunny.getTeam(),  new HashSet<>());
        }
        prevRoom.get(movedBunny.getTeam()).add(movedBunny);
    }

    @Override
    public void detonate(String bunnyName) {
        if (!this.bunnies.containsKey(bunnyName)){
            throw new IllegalArgumentException();
        }

        int roomId = this.bunnies.get(bunnyName).getRoomId();
        HashMap<Integer, HashSet<Bunny>> room = this.roomsById.get(roomId);

        List<Bunny> removedBunnies = new LinkedList<>();

        for(Integer team : room.keySet()){
            if (team == bunnies.get(bunnyName).getTeam()){
                continue;
            }
            for(Bunny bunny : room.get(team)){
                bunny.setHealth(bunny.getHealth()-30);

                if(bunny.getHealth()<=0){
                    bunnies.get(bunnyName).setScore(bunnies.get(bunnyName).getScore()+1);
                    bunnies.remove(bunny.getName());

                    StringBuilder sb = new StringBuilder(bunny.getName());
                    sb.reverse();

                    reversedNameBunnies.remove(sb.toString());

                    bunniesByTeam.get(bunny.getTeam()).remove(bunnyName);
                    removedBunnies.add(bunny);
                }
            }
            for(Bunny b: removedBunnies) {
                room.get(team).remove(b);
            }
        }
    }

    @Override
    public Iterable<Bunny> listBunniesByTeam(int team) {
        if(!bunniesByTeam.containsKey(team)){
           throw new IndexOutOfBoundsException();
        }
        return bunniesByTeam.get(team).values();
    }

    @Override
    public Iterable<Bunny> listBunniesBySuffix(String suffix) {
        if(suffix == ""){
            return reversedNameBunnies.values();
        }

        StringBuilder sb = new StringBuilder(suffix);
        sb.reverse();

        return reversedNameBunnies.subMap(sb.toString(), true, sb.toString() + Character.MAX_VALUE, true).values();
    }
}
