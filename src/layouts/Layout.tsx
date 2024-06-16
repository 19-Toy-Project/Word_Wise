import { useSelector } from "react-redux";
import { Outlet } from "react-router-dom";
import Advertisement from "../components/Advertisement";
import MenuToggle from "../components/MenuToggle";
import Modal from "../components/Modal";
import Toast from "../components/Toast";
import Menubar from "./Menubar";

function Layout() {
  const toast = useSelector((state) => state.toast.toastOptions);
  const menuToggle = useSelector((state) => state.menu.menuOption);
  const modal = useSelector((state) => state.modal.modalOptions);
  return (
    <div>
      {toast && <Toast />}
      {menuToggle && <MenuToggle />}
      {modal && <Modal />}
      <div>
        <Menubar />
        <Advertisement />
        <div className="w-3/4 m-auto mt-[75px] ">
          <Outlet />
        </div>
      </div>
    </div>
  );
}

export default Layout;
