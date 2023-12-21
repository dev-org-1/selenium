// Licensed to the Software Freedom Conservancy (SFC) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The SFC licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;
 
public class Main {
    public static void main(String[] args) {
        // Assume inputColumnName is obtained from user input
        String inputColumnName = "age"; // Assume a valid column name
 
        // Create a Hibernate configuration
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
 
        // Open a session
        try (Session session = sessionFactory.openSession()) {
            // Begin a transaction
            Transaction transaction = session.beginTransaction();
 
            // The modified code using a parameterized query
            String hsql = "update People set " + inputColumnName + " = :value";
            Query query = session.createQuery(hsql);
            query.setParameter("value", null);
            query.executeUpdate();
 
            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the session factory
            sessionFactory.close();
        }
    }
}
package org.openqa.selenium.events;

import java.util.Objects;
import org.openqa.selenium.internal.Require;
import org.openqa.selenium.json.JsonInput;

public final class EventName {

  private final String name;

  public EventName(String name) {
    this.name = Require.nonNull("Type name", name);
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof EventName)) {
      return false;
    }

    EventName that = (EventName) obj;
    return Objects.equals(this.name, that.name);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }

  private String toJson() {
    return name;
  }

  private static EventName fromJson(JsonInput input) {
    return new EventName(input.nextString());
  }
}
