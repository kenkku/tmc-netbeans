package fi.helsinki.cs.tmc.spyware;

import fi.helsinki.cs.tmc.data.Exercise;
import java.util.Date;

public class LoggableEvent {
    
    private String courseName;
    private String exerciseName;
    private String eventType;
    private byte[] data;
    private Date happenedAt;
    
    private transient String key;
    
    public LoggableEvent(Exercise exercise, String eventType, byte[] data) {
        this(exercise.getCourseName(), exercise.getName(), eventType, data);
    }
    
    public LoggableEvent(String courseName, String exerciseName, String eventType, byte[] data) {
        this.courseName = courseName;
        this.exerciseName = exerciseName;
        this.eventType = eventType;
        this.data = data;
        this.happenedAt = new Date();
        
        this.key = courseName + "|" + exerciseName + "|" + eventType;
    }

    public String getCourseName() {
        return courseName;
    }
    
    public String getExerciseName() {
        return exerciseName;
    }

    public String getEventType() {
        return eventType;
    }

    public byte[] getData() {
        return data;
    }

    /**
     * {@code key = course name + "|" + exercise name + "|" + event type}
     */
    public String getKey() {
        return key;
    }

    public Date getHappenedAt() {
        return happenedAt;
    }

    public void setHappenedAt(Date happenedAt) {
        this.happenedAt = happenedAt;
    }
}
