import { SyntheticEvent, useState } from 'react';
import './login.style.css';

const Login = () => {
  //Destructuring assignment -- sets username to first ele of userState array, and setUsername to the second ele of userState array.
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  let updateUsername = (e: SyntheticEvent) => {
    //username = (e.target as HTMLInputElement).value; -- do not need to use this when using Hooks, need to use below.
    setUsername((e.target as HTMLInputElement).value);
  };
  let updatePassword = (e: SyntheticEvent) => {
    setPassword((e.target as HTMLInputElement).value);
  };
  let login = async (e: SyntheticEvent) => {
    console.log(`Username is: ${username}, password is ${password}`);
    if (!username || !password) {
      setErrorMessage('You must have a valid username and password');
    } else {
      setErrorMessage('');
      //   try {
      //   let response = await fetch('localhost:8080/foundations-project/u/login', {
      //     method: 'POST',
      //     headers: {
      //       'Content-Type': 'JSON',
      //     },
      //     credentials: 'include', //property needed to work with java http sessions. Not necessary for JWTs
      //     body: JSON.stringify({ username, password }),
      //   });
      let response = await fetch(
        'http://localhost:8080/foundations-project/r/allTickets'
      );

      if (response.status === 200) {
        let data = await response.json();
        console.log(data);
      } else {
        setErrorMessage('Could not validate the provided credentials');
      }
      //   } catch (err) {
      //     setErrorMessage('Unable to communicate with the server');
      //   }
    }
  };

  return (
    <>
      <h4>Log into ERS</h4>
      <div className="login-container">
        <h3 className="title">Sign in below</h3>
        <div className="input-form">
          <label htmlFor="Username" id="username">
            Username
          </label>
          <input type="text" placeholder="Username" onChange={updateUsername} />
        </div>
        <div className="input-form">
          <label htmlFor="Password" id="password">
            Password
          </label>
          <input type="text" placeholder="Password" onChange={updatePassword} />
        </div>
        <button className="btnSubmit" onClick={login}>
          Sign In
        </button>
        <div>
          <p>{errorMessage}</p>
        </div>
      </div>
    </>
  );
};

export default Login;
