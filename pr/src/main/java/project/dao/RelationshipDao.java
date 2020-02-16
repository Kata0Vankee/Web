package project.dao;

import java.util.ArrayList;
import project.model.Relationship;

public interface RelationshipDao {
    public boolean canCreateRelationship(Relationship rl);
    public void createRelationship(Relationship rl);
    public void submitRelationship(Relationship rl);
    public boolean haveRequest(String id);
    public boolean isValidRelationship(Relationship rl);
    public ArrayList<String> getRelationshipRequestList(String id);
    public void deleteARequest(Relationship rl);
}
