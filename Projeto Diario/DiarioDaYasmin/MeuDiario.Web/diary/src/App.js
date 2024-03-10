import React, { useState, useEffect } from 'react';
import styled from 'styled-components';

const AppContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #001f3f; 
`;

const RatingForm = styled.div`
  background-color: #2980b9; 
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  width: 400px;
  text-align: center;
`;

const TitleInput = styled.input`
  margin-bottom: 15px;
  padding: 10px;
  width: calc(100% - 20px); 
  border: 1px solid #ddd;
  border-radius: 5px;
`;

const DateInput = styled.input`
  margin-bottom: 15px;
  padding: 10px;
  width: calc(100% - 20px); 
  border: 1px solid #ddd;
  border-radius: 5px;
`;

const TypeInput = styled.select`
  margin-bottom: 15px;
  padding: 10px;
  width: calc(100% - 20px); 
  border: 1px solid #ddd;
  border-radius: 5px;
`;

const NotaInput = styled.input`
  margin-bottom: 15px;
  padding: 10px;
  width: calc(100% - 20px); 
  border: 1px solid #ddd;
  border-radius: 5px;
`;

const AddButton = styled.button`
  background-color: #4caf50;
  color: white;
  padding: 10px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  width: 100%;
  transition: background-color 0.3s;

  &:hover {
    background-color: #45a049;
  }
`;

const RatingList = styled.ul`
  list-style-type: none;
  padding: 0;
`;

const RatingItem = styled.li`
  background-color: #fff; 
  padding: 15px;
  border-radius: 5px;
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);
  margin-bottom: 15px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-shrink: 0;
`;

const DeleteButton = styled.button`
  background-color: #e74c3c;
  color: white;
  padding: 8px;
  border: none;
  cursor: pointer;
  border-radius: 50%; 
  margin-left: 10px; 
  transition: background-color 0.3s;

  &:hover {
    background-color: #c0392b;
  }
`;

const App = () => {
  const [ratings, setRatings] = useState([]);
  const [name, setName] = useState('');
  const [date, setDate] = useState('');
  const [type, setType] = useState(''); // Assuming 'type' is a dropdown/select field
  const [nota, setNota] = useState('');

  useEffect(() => {
    fetchRatings();
  }, []);

  const fetchRatings = async () => {
    try {
      const response = await fetch('http://localhost:5219/api/DiarioControllers');
      const data = await response.json();
      setRatings(data);
    } catch (error) {
      console.log(error);
    }
  };

  const createRating = async () => {
    try {
      if (!name || name.trim().length <= 0) {
        alert("Please fill in the name field.");
        return;
      }
      const currentDate = new Date().toISOString().split('T')[0]; 
      if (date <= currentDate) {
        alert("Please select a future date.");
        return;
      }

      const response = await fetch('http://localhost:5219/api/DiarioControllers', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name, date, type, nota }),
      });

      const data = await response.json();
      setRatings([...ratings, data]);
      setDate('');
      setName('');
      setType('');
      setNota('');
      fetchRatings();
    } catch (error) {
      console.log(error);
    }
  };


  const deleteRating = async (id) => {
    try {
      await fetch(`http://localhost:5219/api/DiarioControllers/${id}`, {
        method: 'DELETE',
      });
      setRatings(ratings.filter((rating) => rating.id !== id));
    } catch (error) {
      console.log(error);
    }
  };

  const sortRatings = ratings.sort((a, b) => new Date(a.date) - new Date(b.date));

  return (
    <AppContainer>
      <RatingForm>
        <h1>My Ratings</h1>
        <TitleInput
          type="text"
          placeholder="Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
        <DateInput 
          type="date" 
          value={date} 
          onChange={(e) => setDate(e.target.value)}
        />
        <TypeInput 
          value={type} 
          onChange={(e) => setType(e.target.value)}
        >
          <option value="" disabled>Select Type</option>
          <option value="Book">Book</option>
          <option value="Series">Series</option>
          <option value="Movie">Movie</option>
          <option value="Music">Music</option>
        </TypeInput>
        <NotaInput
          type="number"
          placeholder="Rating"
          value={nota}
          onChange={(e) => setNota(e.target.value)}
        />
        <AddButton onClick={createRating}>Save Rating</AddButton>
        <RatingList>
          {sortRatings.map((rating) => (
            <RatingItem key={rating.id}>
              <div>
                <p>
                  <strong>{rating.date}</strong><br></br>
                  {rating.name} - {rating.type} - Rating: {rating.nota}
                </p> 
                <DeleteButton onClick={() => deleteRating(rating.id)}>
                  X
                </DeleteButton>
              </div>      
            </RatingItem>
          ))}             
        </RatingList>
      </RatingForm>
    </AppContainer>
  );
};

export default App;
