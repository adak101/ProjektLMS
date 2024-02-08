import { useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import ErrorDisplay from "./ErrorDisplay";
import UserContext from "../Context/UserContext";

const headers = {
  "Content-Type": "application/json",
};

function LoginForm() {
  const [data, setData] = useState({
    login: "",
    haslo: "",
  });
  const [err, setErr] = useState({
    error: false,
    message: "",
  });
  const navigate = useNavigate();
  const { setUserInfo } = useContext(UserContext);

  function handleError(message) {
    setErr({ error: true, message: message });
  }

  function handleclearState() {
    setData({ login: "", haslo: "" });
  }

  function handleInput(e) {
    setData({ ...data, [e.target.name]: e.target.value });
  }

  const handleSubmit = async function (e) {
    e.preventDefault();

    try {
      const res = await fetch("/api/v1/auth/login", {
        method: "POST",
        headers: headers,
        body: JSON.stringify(data),
      });

      if (!res.ok) {
        throw new Error("Blad w logowaniu");
      }
      const answer = await res.json();
      setUserInfo({ id: answer.id, login: answer.login, token: answer.token });
      localStorage.setItem("token", JSON.stringify(answer.token));
      localStorage.setItem("userId", JSON.stringify(answer.id));
      navigate("/threads");
    } catch (err) {
      handleclearState();
      handleError(err.message);
    }
  };

  return (
    <div className="login-form">
      <h1 className="login-form-element">Zaloguj się:</h1>
      <form className="login-form-flex">
        <label className="login-form-element">
          Login:
          <input
            type="text"
            placeholder="Username..."
            name="login"
            onChange={handleInput}
            value={data.login}
          />
        </label>
        <label className="login-form-element">
          Password:
          <input
            type="password"
            name="haslo"
            placeholder="Password..."
            onChange={handleInput}
            value={data.haslo}
          />
        </label>
        <div>
          <button
            className="login-form-element"
            onClick={(e) => handleSubmit(e)}
          >
            Zaloguj
          </button>
          <div className="flex mt-10 items-center justify-between gap-x-3">
          </div>
        </div>
      </form>
      {err.error && <ErrorDisplay message={err.message} />}
    </div>
  );
}

export default LoginForm;
