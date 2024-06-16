import { useEffect, useState } from "react";
import { useLoaderData, useNavigate, useParams } from "react-router-dom";
import SearchBar from "../../components/SearchBar";

const menuObj = [
  {
    key: 0,
    value: "초급",
    href: "/beginner",
  },
  { key: 1, value: "중급", href: "/middle" },
  { key: 2, value: "고급", href: "/advanced" },
  { key: 3, value: "회화", href: "/conversation" },
];
function LevelPage() {
  const word = useLoaderData();
  const { level } = useParams();
  const navigate = useNavigate();
  const [pageTitle, setPageTitle] = useState<string>("");

  useEffect(() => {
    let levelTitle = pageTitle;
    if (level == "beginner") levelTitle = "초급";
    if (level == "middle") levelTitle = "중급";
    if (level == "advanced") levelTitle = "고급";
    if (level == "conversation") levelTitle = "회화";
    setPageTitle(levelTitle);
  }, [level, pageTitle]);

  return (
    <div className="c-container">
      <div>
        <h1 className="font-bold text-2xl text-center m-5">{pageTitle}</h1>
        <SearchBar />
      </div>
      <div className="r-container">
        <div className="container basis-1/3">
          <h1 className="text-white-color">메뉴</h1>
          <hr />
          {menuObj.map((menu) => (
            <div
              key={menu.key}
              className="hover:bg-white-color p-4 rounded-lg"
              onClick={() => navigate("/study" + menu.href)}
            >
              <h1 className="text-white-color hover:text-pink-color">
                {menu.value}
              </h1>
            </div>
          ))}
        </div>
        <div className="container">
          <h1 className="text-white-color">단어 리스트</h1>
        </div>
        <div className="container basis-1/3">
          <h1 className="text-white-color">최근 본 단어</h1>
          <hr />
          <div className="overflow-y-auto">
            <div className="r-container hover:bg-white-color p-4 rounded-lg">
              <h1>Sleep</h1>
              <h1>초급</h1>
            </div>
          </div>
        </div>
      </div>

      <div>페이지</div>
    </div>
  );
}

export default LevelPage;
