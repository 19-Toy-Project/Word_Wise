import { PropsWithChildren } from "react";
import { useDispatch } from "react-redux";
import { modalClose } from "../../redux/slices/modal.slice";

function BackDrop({ children }: PropsWithChildren) {
  const dispatch = useDispatch();
  return (
    <div
      className="back-drop flex justify-center items-center"
      onClick={() => dispatch(modalClose())}
    >
      {children}
    </div>
  );
}

export default BackDrop;
