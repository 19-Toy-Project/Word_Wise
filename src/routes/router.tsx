import { createBrowserRouter } from "react-router-dom";
import Layout from "../layouts/Layout";
import AboutPage from "../pages/AboutPage";
import DetailWordPage from "../pages/DetailWordPage";
import HomePage from "../pages/HomePage";
import LevelPage from "../pages/LevelPage";
import LevelPageLoader from "../pages/LevelPage/LevelPage.loader";
import MyPage from "../pages/MyPage";
import MyPageLoader from "../pages/MyPage/MyPage.loader";

const router = createBrowserRouter([
  {
    element: <Layout />,
    children: [
      {
        path: "/",
        element: <HomePage />,
      },
      {
        path: "/about",
        element: <AboutPage />,
      },
      {
        path: "/user/:userId",
        element: <MyPage />,
        loader: MyPageLoader,
      },
      {
        path: "/study/:level",
        element: <LevelPage />,
        loader: LevelPageLoader,
      },
      {
        path: "/study/:level/detail/:wordId",
        element: <DetailWordPage />,
      },
    ],
  },
]);

export default router;
