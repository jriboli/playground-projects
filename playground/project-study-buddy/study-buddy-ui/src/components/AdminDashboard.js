import React, { useEffect, useState } from 'react';
import { getCheatSheets, createCheatSheet } from '../api';

const AdminDashboard = () => {
  const [cheatSheets, setCheatSheets] = useState([]);
  const [newCheatSheet, setNewCheatSheet] = useState({
    topic: '',
    package: '',
    method: '',
    description: '',
    code_example: ''
  });

  useEffect(() => {
    const fetchCheatSheets = async () => {
      try {
        const data = await getCheatSheets();
        setCheatSheets(data);
      } catch (error) {
        setError("Failed to load cheat sheets.");
      }
    };

    fetchCheatSheets();
  }, []);

  const handleCreateCheatSheet = async () => {
    try {
      await createCheatSheet(newCheatSheet);
      setNewCheatSheet({
        topic: '',
        package: '',
        method: '',
        description: '',
        code_example: ''
      });
      const data = await getCheatSheets();
      setCheatSheets(data);
    } catch (error) {
      setError("Failed to create cheat sheet.");
    }
  };

  return (
    <div>
      <h2>Admin Dashboard</h2>
      <h3>Create New Cheat Sheet</h3>
      <input
        type="text"
        placeholder="Topic"
        value={newCheatSheet.topic}
        onChange={(e) => setNewCheatSheet({ ...newCheatSheet, topic: e.target.value })}
      />
      <input
        type="text"
        placeholder="Package"
        value={newCheatSheet.package}
        onChange={(e) => setNewCheatSheet({ ...newCheatSheet, package: e.target.value })}
      />
      <input
        type="text"
        placeholder="Method"
        value={newCheatSheet.method}
        onChange={(e) => setNewCheatSheet({ ...newCheatSheet, method: e.target.value })}
      />
      <input
        type="text"
        placeholder="Description"
        value={newCheatSheet.description}
        onChange={(e) => setNewCheatSheet({ ...newCheatSheet, description: e.target.value })}
      />
      <textarea
        placeholder="Code Example"
        value={newCheatSheet.code_example}
        onChange={(e) => setNewCheatSheet({ ...newCheatSheet, code_example: e.target.value })}
      />
      <button onClick={handleCreateCheatSheet}>Create Cheat Sheet</button>

      <h3>Existing Cheat Sheets</h3>
      <ul>
        {cheatSheets.map((sheet) => (
          <li key={sheet.id}>{sheet.topic} - {sheet.method}</li>
        ))}
      </ul>
    </div>
  );
};

export default AdminDashboard;
