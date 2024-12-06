package control;

import io.quarkus.liquibase.LiquibaseFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import liquibase.Liquibase;
import liquibase.exception.LiquibaseException;

@ApplicationScoped
public class LiquibaseService {
    @Inject
    LiquibaseFactory liquibaseFactory;

    public void updateDatabase() {
        try (Liquibase liquibase = liquibaseFactory.createLiquibase()) {
            liquibase.update(); // Updates the database to the latest state
        } catch (LiquibaseException e) {
            throw new RuntimeException("Failed to update database", e);
        }
    }

    public void rollbackDatabase(String tag) {
        try (Liquibase liquibase = liquibaseFactory.createLiquibase()) {
            liquibase.rollback(tag, (String) null); // Rolls back to the specified tag
        } catch (LiquibaseException e) {
            throw new RuntimeException("Failed to rollback database", e);
        }
    }

    public void rollbackToCount(int changesToRollback) {
        try (Liquibase liquibase = liquibaseFactory.createLiquibase()) {
            liquibase.rollback(changesToRollback, (String) null); // Rollback N changes
        } catch (LiquibaseException e) {
            throw new RuntimeException("Rollback failed", e);
        }
    }

    public void tagDatabase(String tag) {
        try (Liquibase liquibase = liquibaseFactory.createLiquibase()) {
            liquibase.tag(tag); // Tags the current state
        } catch (LiquibaseException e) {
            throw new RuntimeException("Failed to tag database", e);
        }
    }
}
