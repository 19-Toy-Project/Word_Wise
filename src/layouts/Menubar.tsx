import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { menuToggle } from "../redux/slices/menu.slice";
import Logo from "../styles/images/logo-icon.png";
function Menubar() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const handleMenu = () => {
    dispatch(menuToggle());
  };
  return (
    <div className="fixed w-full top-0 left-0 right-0 z-20 bg-black-color text-white-color shadow-md">
      <div className="r-container w-3/4 m-auto">
        <img
          className="w-20 hover:cursor-pointer"
          src={Logo}
          onClick={() => navigate("/")}
        />
        <button onClick={handleMenu}>메뉴</button>
      </div>
    </div>
  );
}

export default Menubar;
