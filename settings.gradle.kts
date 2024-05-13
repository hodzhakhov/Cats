rootProject.name = "Cats"
include("CatMicroservice")
include("OwnerMicroservice")
include("UserMicroservice")
include("CatMicroservice:CatClient")
findProject(":CatMicroservice:CatClient")?.name = "CatClient"
include("CatMicroservice:CatController")
findProject(":CatMicroservice:CatController")?.name = "CatController"
include("OwnerMicroservice:OwnerClient")
findProject(":OwnerMicroservice:OwnerClient")?.name = "OwnerClient"
include("OwnerMicroservice:OwnerController")
findProject(":OwnerMicroservice:OwnerController")?.name = "OwnerController"
