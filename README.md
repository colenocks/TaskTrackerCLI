# Task Tracker CLI

Task tracker is a project used to track and manage your tasks.

In this task, I have built a simple command line interface (CLI) application to track:
- what you need to do,
- what you have done, and
- what you are currently working on.

Key phrases: Java filesystem, handling user inputs, CLI application.

[Courtesy, Roadmap.sh projects.](https://roadmap.sh/projects/task-tracker)

## Pre-requisites

- Make sure to have JDK 21 installed.
- Use any IDE of choice (I use IntelliJ).
- Clone the repository.
- Run the application.

## How it works

1. User writes a set of arguments into the CLI. These args are separated by whitespace.
2. The first argument must be `task-cli`.
3. These argument are process and validated into an array. The second argument must be an action. The possible actions
   include `list`, `add`, `update`, `delete` `mark-done` & `mark-in-progress`.
4. The corresponding arguments (if any) are parsed accordingly based on the action provided.

### Positional Arguments Structure

The input args provided will be collected as thus:

```comments
 add -> ["task-cli", "add", <description>]
 update -> ["task-cli", "update", <id>, <description>]
 delete -> ["task-cli", "delete", <id>]
 list -> ["task-cli", "list", Optional<status>]
 mark-* -> ["task-cli", "mark-*", <id>]
```

For example, if the user enters

   ```
   task-cli list in-progress blah blah"
   ```

The action here is `list`. A list expects zero or 1 subsequent argument. Since the third argument is a valid `status` (
The statuses are either of `todo`, `in-progress` & `done`), the
rest of the supplied arguments will be ignored, otherwise an exception is thrown.

For a detailed guide, run:

```
task-cli help
```

### Shape of JSON object

```json
{
  "tasks": [
    {
      "id": "1",
      "description": "Hello World",
      "status": "todo",
      "createdAt": "14-09-2024 08:01:01",
      "updatedAt": "14-09-2024 08:01:01"
    }
  ]
}
```