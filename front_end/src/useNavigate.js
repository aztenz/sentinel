// useNavigate.js
import { useContext } from 'react';
import { useHistory } from 'react-router';

const useNavigate = () => {
  const history = useHistory();
  
  const navigateTo = (path) => {
    history.push(path);
  };

  return navigateTo;
};

export default useNavigate;
