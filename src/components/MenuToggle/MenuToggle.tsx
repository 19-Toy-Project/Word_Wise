import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { menuToggle } from "../../redux/slices/menu.slice";
import { modalOpen } from "../../redux/slices/modal.slice";
import DefaultImage from "../../styles/images/default-profile.jpg";
function MenuToggle() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const menuOption = useSelector((state) => state.menu.menuOption);

  const [animationClass, setAnimationClass] = useState<string>("");

  useEffect(() => {
    setAnimationClass(menuOption ? "animate-slideIn" : "animate-slideOut");
  }, [menuOption]);

  const handleBackdropClick = () => {
    setAnimationClass("animate-slideOut");
    setTimeout(() => dispatch(menuToggle()), 300);
  };

  const handleSignOut = () => {
    dispatch(modalOpen("로그아웃하시겠습니까?"));
  };
  return (
    <div
      className="back-drop fixed inset-0 bg-black bg-opacity-50"
      onClick={handleBackdropClick}
    >
      <div
        className={`bg-white w-96 h-full shadow-lg p-6 ${animationClass}`}
        onClick={(e) => e.stopPropagation()}
      >
        <div className="flex flex-col  gap-5 text-center">
          <img className="rounded-full w-40 m-auto" src={DefaultImage} />
          <h1 className="font-bold text-xl">누가 소리를 내었는가</h1>
          <div className="flex flex-row  flex-wrap gap-7">
            <button className="btn-menu" onClick={() => navigate("/")}>
              홈
            </button>
            <button className="btn-menu">로그인</button>
            <button className="btn-menu" onClick={() => navigate(`/user/${1}`)}>
              MYPAGE
            </button>
            <button className="btn-menu" onClick={handleSignOut}>
              로그아웃
            </button>
            <button className="btn-menu" onClick={() => navigate("/about")}>
              ABOUT
            </button>
            <button
              className="btn-menu"
              onClick={() => navigate("/study/beginner")}
            >
              초급
            </button>
            <button
              className="btn-menu"
              onClick={() => navigate("/study/middle")}
            >
              중급
            </button>
            <button
              className="btn-menu"
              onClick={() => navigate("/study/advanced")}
            >
              고급
            </button>
            <button
              className="btn-menu"
              onClick={() => navigate("/study/conversation")}
            >
              회화
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default MenuToggle;
