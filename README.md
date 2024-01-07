<h1>TaskForge - Task Management Application</h1>

<p>TaskForge is a simple task management system built using Spring Boot.</p>

<h2>Key Features</h2>

<ul>
  <li><input type="checkbox" disabled> <strong>Task CRUD Operations:</strong> Add, update, and remove tasks.</li>
  <li><input type="checkbox" disabled> <strong>Task Listing:</strong> View all registered tasks.</li>
</ul>

<h2>Prerequisites</h2>

<ul>
  <li>Java 17 or higher</li>
  <li>Maven</li>
</ul>

<h2>Setup and Execution</h2>

<ol>
  <li><strong>Clone the repository:</strong><br>
    <code>git clone https://github.com/fridry/taskforge.git</code></li>
  <li><strong>Navigate to the project directory:</strong><br>
    <code>cd taskforge</code></li>
  <li><strong>Run the application:</strong><br>
    <code>mvn spring-boot:run</code></li>
  <li><strong>Access the application:</strong><br>
    The application will be available at <a href="http://localhost:8080">http://localhost:8080</a></li>
</ol>

<h2>Available APIs</h2>

<ul>
  <li><strong>GET /api/tasks:</strong> Returns all registered tasks.</li>
  <li><strong>POST /api/tasks:</strong> Creates a new task.</li>
  <li><strong>GET /api/tasks/{id}:</strong> Returns details of a specific task.</li>
  <li><strong>PATCH /api/tasks/{id}:</strong> Partially updates task data.</li>
  <li><strong>DELETE /api/tasks/{id}:</strong> Deletes an existing task.</li>
</ul>

<h2>Contribution</h2>

<p>Contributions are welcome! If you encounter any issues or have suggestions, please open an <a href="https://github.com/your-username/taskforge/issues">issue</a> or submit a pull request.</p>

<h2>Author</h2>

<p>Fridry - <a href="https://github.com/Fridry">GitHub</a></p>

<h2>License</h2>

<p>This project is licensed under the <a href="https://opensource.org/licenses/MIT">MIT License</a>.</p>
